package com.irfanirawansukirman.data.repository.base

import com.irfanirawansukirman.data.common.coroutine.CoroutineContextProvider
import com.irfanirawansukirman.data.common.util.Connectivity
import com.irfanirawansukirman.data.network.base.DomainMapper
import com.irfanirawansukirman.domain.model.Failure
import com.irfanirawansukirman.domain.model.HttpError
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.Success
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject

abstract class BaseRepository<T : Any, R : DomainMapper<T>> : KoinComponent {

    private val connectivity: Connectivity by inject()
    private val contextProvider: CoroutineContextProvider by inject()

    /**
     * Use this when communicating only with the api service
     */
    protected suspend fun fetchData(dataProvider: suspend () -> Result<T>): Result<T> {
        return if (connectivity.isNetworkAvailable()) {
            withContext(contextProvider.io) {
                dataProvider()
            }
        } else {
            Failure(HttpError(Throwable("Terjadi kesalahan pada jaringan internet")))
        }
    }

    /**
     * Use this if you need to cache data after fetching it from the api, or retrieve something from cache
     */
    protected suspend fun fetchData(
        apiDataProvider: suspend () -> Result<T>,
        dbDataProvider: suspend () -> R
    ): Result<T> {
        return if (connectivity.isNetworkAvailable()) {
            withContext(contextProvider.io) {
                apiDataProvider()
            }
        } else {
            withContext(contextProvider.io) {
                val dbResult = dbDataProvider()
                if (dbResult != null) Success(dbResult.mapToDomainModel()) else Failure(
                    HttpError(
                        Throwable("")
                    )
                )
            }
        }
    }
}