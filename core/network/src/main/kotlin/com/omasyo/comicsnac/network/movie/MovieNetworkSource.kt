package com.omasyo.comicsnac.network.movie

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.common.Sort
import com.omasyo.comicsnac.network.movie.models.MovieDetailsResponse
import com.omasyo.comicsnac.network.movie.models.MovieListResponse

interface MovieNetworkSource : NetworkSource {
    suspend fun getMovieDetails(apiKey: String, id: String): Result<MovieDetailsResponse>

    suspend fun getRecentMovies(
        apiKey: String,
        pageSize: Int,
        offset: Int
    ): Result<MovieListResponse>

    suspend fun getAllMovies(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sortReleaseDate: Sort = Sort.Descending
    ): Result<MovieListResponse>

    suspend fun getMoviesWithId(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sortReleaseDate: Sort = Sort.Descending,
        moviesId: List<Int>
    ): Result<MovieListResponse>
}