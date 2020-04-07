package com.irfanirawansukirman.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.data.common.coroutine.TestCoroutineContextProvider
import com.irfanirawansukirman.domain.interaction.language.LanguageUseCase
import com.irfanirawansukirman.domain.model.Failure
import com.irfanirawansukirman.domain.model.HttpError
import com.irfanirawansukirman.domain.model.Success
import com.irfanirawansukirman.domain.model.response.LanguangeMapper
import com.irfanirawansukirman.home.presentation.HomeVM
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class HomeVMTest {

    private val coroutineContext = TestCoroutineContextProvider()
    private val getLanguage: LanguageUseCase = mock()
    private val viewModel by lazy { HomeVM(getLanguage, coroutineContext) }

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val question = "Hello Irfan?"

    @Test
    fun `getLanguage with livedata status is success`() = runBlocking {
        // given
        whenever(getLanguage()).thenReturn(Success(LanguangeMapper(question)))

        // when
        viewModel.getLanguage()

        // then
        assertEquals(ViewState.Status.SUCCESS, viewModel.languageState.value?.status)
    }

    @Test
    fun `getLanguage with livedata status is failed`() = runBlocking {
        // given
        whenever(getLanguage()).thenReturn(Failure(HttpError(Throwable(""))))

        // when
        viewModel.getLanguage()

        // then
        assertEquals(ViewState.Status.ERROR, viewModel.languageState.value?.status)
    }

    @Test
    fun `getLanguage with expected result`() = runBlocking {
        // given
        whenever(getLanguage()).thenReturn(Success(LanguangeMapper(question)))

        // when
        viewModel.getLanguage()

        // then
        assertEquals(question, viewModel.languageState.value?.data?.question)
    }

}