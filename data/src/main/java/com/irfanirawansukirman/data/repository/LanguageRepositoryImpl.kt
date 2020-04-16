package com.irfanirawansukirman.data.repository

import com.irfanirawansukirman.data.network.base.getData
import com.irfanirawansukirman.data.network.service.LanguageApi
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import com.irfanirawansukirman.domain.repository.LanguageRepository

class LanguageRepositoryImpl(
    private val languageService: LanguageApi
) : LanguageRepository {
    override suspend fun getLanguage(): Result<LanguangeMapper> {
        return languageService.getLanguage().getData()
    }
}