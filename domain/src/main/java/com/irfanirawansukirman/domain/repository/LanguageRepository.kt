package com.irfanirawansukirman.domain.repository

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.LanguangeMapper

interface LanguageRepository {
    suspend fun getLanguage(): Result<LanguangeMapper>
}