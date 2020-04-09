package com.irfanirawansukirman.domain.base

import com.irfanirawansukirman.domain.model.Result

interface BaseUseCase<T : Any, R : Any> {
    suspend fun invoke(param: T): Result<R>
}