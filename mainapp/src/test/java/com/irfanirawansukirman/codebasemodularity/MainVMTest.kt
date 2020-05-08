package com.irfanirawansukirman.codebasemodularity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irfanirawansukirman.abstraction.util.ext.asListOfType
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.codebasemodularity.presentation.MainVM
import com.irfanirawansukirman.data.common.coroutine.TestCoroutineContextProvider
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
import com.irfanirawansukirman.domain.model.Failure
import com.irfanirawansukirman.domain.model.HttpError
import com.irfanirawansukirman.domain.model.Success
import com.irfanirawansukirman.domain.model.response.MovieInfoMapper
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MainVMTest {

    private val coroutineContext = TestCoroutineContextProvider()
    private val useCase: MoviesUseCase = mock()
    private val viewModel by lazy { MainVM(useCase, coroutineContext) }

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    private val moviesList = listOf(
        MoviesResult(
            false,
            "",
            listOf(1),
            1,
            "",
            "",
            "",
            0.0,
            "",
            "", "",
            false,
            0.0,
            1
        )
    )

    @Test
    fun `getMoviesList with livedata status is success`() = runBlocking {
        // given
        whenever(useCase.getMovies("", "")).thenReturn(Success(MovieInfoMapper(moviesList)))

        // when
        viewModel.getMoviesRemoteState()

        // then
        assertEquals(ViewState.Status.SUCCESS, viewModel.getMoviesRemoteState().value?.status)
    }

    @Test
    fun `getMoviesList with livedata status is failed`() = runBlocking {
        // given
        whenever(useCase.getMovies("", "")).thenReturn(Failure(HttpError(Throwable(""))))

        // when
        viewModel.getMoviesRemoteState()

        // then
        assertEquals(ViewState.Status.ERROR, viewModel.getMoviesRemoteState().value?.status)
    }

    fun `getMovieList with expected list`() = runBlocking {
        // given
        whenever(useCase.getMovies("", "")).thenReturn(Success(MovieInfoMapper(moviesList)))

        // when
        viewModel.getMoviesRemoteState()

        // then
        assertEquals(
            moviesList,
            viewModel.getMoviesRemoteState().value?.data?.movieList?.asListOfType<MoviesResult>()
        )
    }
}
