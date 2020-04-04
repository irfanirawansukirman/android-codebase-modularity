package com.irfanirawansukirman.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.domain.interaction.language.LanguageUseCase
import com.irfanirawansukirman.domain.model.Success
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import com.irfanirawansukirman.home.presentation.HomeVM
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)

class HomeViewModelUT {

    private val languageUseCase: LanguageUseCase = mock()

    private val viewModel by lazy { HomeVM(languageUseCase) }

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Test
    fun shouldReturnImagePath() = runBlocking {
        // given
        whenever(languageUseCase()).thenReturn(Success(LanguangeMapper("Hay Irfan")))

        // when
        viewModel.getLanguage()

        // then
        assertEquals(ViewState.Status.SUCCESS, viewModel.uiState.value?.status)
    }
}