package com.irfanirawansukirman.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.data.common.base.BaseVM
import com.irfanirawansukirman.domain.interaction.language.LanguageUseCase
import com.irfanirawansukirman.domain.model.onFailure
import com.irfanirawansukirman.domain.model.onSuccess
import com.irfanirawansukirman.domain.model.response.LanguangeMapper

interface HomeContract {
    fun getLanguage()

    fun setupImagePath(imagePath: String)
}

class HomeVM(private val languageUseCase: LanguageUseCase) :
    BaseVM<LanguangeMapper, HomeViewEffects>(), HomeContract {

    private val _imagePath = MutableLiveData<String>()
    val imagePath: LiveData<String>
        get() = _imagePath

    override fun getLanguage() = executeUseCase({
        languageUseCase()
            .onSuccess {
                _uiState.value = ViewState.success(it)
            }
            .onFailure {
                _uiState.value = ViewState.error(it.throwable)
            }
    }, {
        _uiState.value = ViewState.connectionLost()
    })

    override fun setupImagePath(imagePath: String) {
        this._imagePath.value = imagePath
    }

}