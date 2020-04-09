package com.irfanirawansukirman.domain.interaction.language

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import com.irfanirawansukirman.domain.repository.LanguageRepository

class LanguageUseCaseImpl(private val languageRepository: LanguageRepository) : LanguageUseCase {

    override suspend fun invoke(): Result<LanguangeMapper> = languageRepository.getLanguage()
}