package com.irfanirawansukirman.codebasemodularity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.irfanirawansukirman.abstraction.util.ext.asListOfType
import com.irfanirawansukirman.codebasemodularity.presentation.MainVM
import com.irfanirawansukirman.data.common.coroutine.TestCoroutineContextProvider
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfo
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.mockito.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule

class MainVMTest {
//    private val getMoviesList: MoviesUseCase = mock()
//    private val coroutineContext = TestCoroutineContextProvider()
//    private val mainVM by lazy { MainVM(getMoviesList) }

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock lateinit var result: Observer<List<MoviesResult>>
    @Mock lateinit var error: Observer<String>

    @Captor lateinit var argResultCaptor: ArgumentCaptor<List<MoviesResult>>
    @Captor lateinit var argErrorCaptor: ArgumentCaptor<String>

    @Mock lateinit var useCase: MoviesUseCase

    private lateinit var viewModel: MainVM

    private val movies = listOf(
        MoviesResult(
            true, "", listOf(1, 2, 3), 1, "", "", "",
            0.0, "", "", "", true, 0.0, 1
        )
    )

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @Before fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(Dispatchers.Unconfined)

        viewModel = MainVM(useCase)
//        viewModel.uiState.observeForever()
//        viewModel.error.observeForever(error)
    }

    @Test
    fun `test getMoviesList sets liveData value when success`() = runBlocking {
        val returnValue = Result.Success(MovieInfo(movies))
        `when`(useCase.invoke("")).thenReturn(returnValue)
        viewModel.getMovieList()
        verify(result, Mockito.atLeastOnce()).onChanged(argResultCaptor.capture())
        assertEquals(
            returnValue.data.movieList?.asListOfType<MoviesResult>(),
            argResultCaptor.allValues.first()
        )
    }
}