package com.irfanirawansukirman.codebasemodularity

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.irfanirawansukirman.abstraction.util.ext.asListOfType
import com.irfanirawansukirman.abstraction.util.state.ViewState
import com.irfanirawansukirman.codebasemodularity.presentation.MainVM
import com.irfanirawansukirman.data.common.coroutine.TestCoroutineContextProvider
import com.irfanirawansukirman.data.network.model.MoviesResult
import com.irfanirawansukirman.domain.interaction.movies.MoviesUseCase
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
    private val getMoviesList: MoviesUseCase = mock()
    private val mainVM by lazy { MainVM(getMoviesList, coroutineContext) }

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
        whenever(getMoviesList("")).thenReturn(Success(MovieInfoMapper(moviesList)))

        // when
        mainVM.getMoviesList()

        // then
        assertEquals(ViewState.Status.SUCCESS, mainVM.movieInfoState.value?.status)
    }

    @Test
    fun `getMovieList with expected list`() = runBlocking {
        // given
        whenever(getMoviesList("")).thenReturn(Success(MovieInfoMapper(moviesList)))

        // when
        mainVM.getMoviesList()

        // then
        assertEquals(
            moviesList,
            mainVM.movieInfoState.value?.data?.movieList?.asListOfType<MoviesResult>()
        )
    }

    @Test
    fun `getMovieList with failed`() = runBlocking {
        // given
        whenever(getMoviesList("")).thenReturn(null)

        // when
        mainVM.getMoviesList()

        // then
        assertEquals(
            null,
            null
        )
    }
}