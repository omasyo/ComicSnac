package com.omasyo.comicsnac.home

import androidx.compose.runtime.Immutable
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.model.movie.Movie
import com.omasyo.comicsnac.model.publisher.Publisher
import com.omasyo.comicsnac.model.series.Series
import com.omasyo.comicsnac.model.volume.Volume

sealed interface HomeCategoryUiState<out T> {
    val refresh: () -> Unit
}

data object Loading : HomeCategoryUiState<Nothing> {
    override val refresh: () -> Unit = {}
}

data class Error(val error: RepositoryResponse.Error, override val refresh: () -> Unit) :
    HomeCategoryUiState<Nothing>

@Immutable
data class Success<T>(val contents: List<T>, override val refresh: () -> Unit = {}) :
    HomeCategoryUiState<T>

typealias IssuesUiState = HomeCategoryUiState<Issue>

typealias CharactersUiState = HomeCategoryUiState<Character>

typealias VolumesUiState = HomeCategoryUiState<Volume>

typealias MoviesUiState = HomeCategoryUiState<Movie>

typealias SeriesUiState = HomeCategoryUiState<Series>

typealias PublishersUiState = HomeCategoryUiState<Publisher>
