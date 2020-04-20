package com.irfanirawansukirman.data.network.base

import com.irfanirawansukirman.domain.model.Failure
import com.irfanirawansukirman.domain.model.HttpError
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.Success
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

interface DomainMapper<T : Any> {
    fun mapToDomainModel(): T
}

interface RoomMapper<out T : Any> {
    fun mapToRoomEntity(): T
}

inline fun <T : Any> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (isSuccessful) body()?.run(action)
    return this
}

inline fun <T : Any> Response<T>.onFailure(action: (HttpError) -> Unit) {
    if (!isSuccessful) errorBody()?.run { action(HttpError(Throwable(message()), code())) }
}

/**
 * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
 */

inline fun <T : RoomMapper<R>, R : DomainMapper<U>, U : Any> Response<T>.getData(
    cacheAction: (R) -> Unit,
    fetchFromCacheAction: () -> R
): Result<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return Success(databaseEntity.mapToDomainModel())
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) Success(cachedModel.mapToDomainModel()) else Failure(
                HttpError(
                    Throwable("")
                )
            )
        }
        return Failure(HttpError(Throwable("")))
    } catch (e: IOException) {
        return Failure(HttpError(Throwable("")))
    }
}

/**
 * Use this when communicating only with the api service
 */
fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): Result<R> {
    try {
        onSuccess { return Success(it.mapToDomainModel()) }
        onFailure { return Failure(it) }
        return Failure(HttpError(Throwable("Can't get data from API"), 214))
    } catch (e: Throwable) {
        return when (e) {
            is IOException -> Failure(HttpError(Throwable("Error I/O"), 212))
            is HttpException -> {
                val code: Int
                val message: String
                when (e.code()) {
                    400 -> {
                        code = 400
                        message = "Bad request"
                    }
                    401 -> {
                        code = 401
                        message = "Unauthorised"
                    }
                    403 -> {
                        code = 403
                        message = "Forbidden"
                    }
                    404 -> {
                        code = 404
                        message = "Service not found"
                    }
                    500 -> {
                        code = 500
                        message = "Internal server error"
                    }
                    else -> {
                        code = 0
                        message = "Something went wrong"
                    }
                }
                Failure(HttpError(Throwable(message), code))
            }
            else -> Failure(HttpError(Throwable(e.message), 213))
        }
    }
}