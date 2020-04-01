package com.irfanirawansukirman.domain.interaction.language

import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.LanguangeMapper

interface LanguageUseCase {
    suspend operator fun invoke(): Result<LanguangeMapper>
}