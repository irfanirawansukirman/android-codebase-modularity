package com.irfanirawansukirman.home.presentation

import com.irfanirawansukirman.abstraction.util.state.ConnectionLost
import com.irfanirawansukirman.abstraction.util.state.Error
import com.irfanirawansukirman.abstraction.util.state.Success
import com.irfanirawansukirman.data.common.base.BaseVM
import com.irfanirawansukirman.domain.interaction.language.LanguageUseCase
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.LanguangeMapper

interface HomeContract {
    fun getLanguage()
}

class HomeVM(private val languageUseCase: LanguageUseCase) :
    BaseVM<LanguangeMapper, HomeViewEffects>(), HomeContract {
    override fun getLanguage() = executeUseCase({
        languageUseCase()
            .onSuccess {
                _uiState.value = Success(it)
            }
            .onFailure {
                _uiState.value = Error(it.throwable)
            }
    }, {
        _uiState.value = ConnectionLost()
    })

}