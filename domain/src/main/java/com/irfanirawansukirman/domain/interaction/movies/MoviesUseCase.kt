package com.irfanirawansukirman.domain.interaction.movies

import com.irfanirawansukirman.domain.base.BaseUseCase
import com.irfanirawansukirman.domain.model.Result
import com.irfanirawansukirman.domain.model.response.MovieInfo

interface MoviesUseCase : BaseUseCase<String, MovieInfo> {

    override suspend operator fun invoke(param: String): Result<MovieInfo>

}