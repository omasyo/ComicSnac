package com.omasyo.comicsnac.categories.movie

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.movie.MovieRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.movie.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class MovieViewModel @Inject constructor(
    movieRepository: MovieRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Movie>(settingsRepository) {

    override val items =
        movieRepository.getAllMovies().cachedIn(viewModelScope)

}

