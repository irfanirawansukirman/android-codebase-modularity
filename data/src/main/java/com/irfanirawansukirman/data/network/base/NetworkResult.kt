package com.irfanirawansukirman.data.network.base

import com.irfanirawansukirman.domain.model.HttpError
import com.irfanirawansukirman.domain.model.Result
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
    fetchFromCacheAction: () -> R): Result<U> {
    try {
        onSuccess {
            val databaseEntity = it.mapToRoomEntity()
            cacheAction(databaseEntity)
            return Result.Success(databaseEntity.mapToDomainModel())
        }
        onFailure {
            val cachedModel = fetchFromCacheAction()
            if (cachedModel != null) Result.Success(cachedModel.mapToDomainModel()) else Result.Failure(HttpError(Throwable("")))
        }
        return Result.Failure(HttpError(Throwable("")))
    } catch (e: IOException) {
        return Result.Failure(HttpError(Throwable("")))
    }
}

/**
 * Use this when communicating only with the api service
 */
fun <T : DomainMapper<R>, R : Any> Response<T>.getData(): Result<R> {
    try {
        onSuccess { return Result.Success(it.mapToDomainModel()) }
        onFailure { return Result.Failure(it) }
        return Result.Failure(HttpError(Throwable("")))
    } catch (e: IOException) {
        return Result.Failure(HttpError(Throwable("")))
    }
}