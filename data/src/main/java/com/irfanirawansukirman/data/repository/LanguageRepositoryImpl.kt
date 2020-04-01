package com.irfanirawansukirman.data.repository

import com.irfanirawansukirman.data.network.base.getData
import com.irfanirawansukirman.data.network.model.LanguageResponse
import com.irfanirawansukirman.data.network.service.LanguageApi
import com.irfanirawansukirman.data.repository.base.BaseRepository
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import com.irfanirawansukirman.domain.repository.LanguageRepository

class LanguageRepositoryImpl(private val languageService: LanguageApi) :
    BaseRepository<LanguangeMapper, LanguageResponse>(), LanguageRepository {
    override suspend fun getLanguage(): Result<LanguangeMapper> {
        return fetchData(dataProvider = { languageService.getLanguage().getData() })
    }
}