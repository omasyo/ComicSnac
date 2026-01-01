package com.omasyo.comicsnac.details.movie

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omasyo.comicsnac.data.character.CharacterRepository
import com.omasyo.comicsnac.data.location.LocationRepository
import com.omasyo.comicsnac.data.movie.MovieRepository
import com.omasyo.comicsnac.data.`object`.ObjectRepository
import com.omasyo.comicsnac.data.team.TeamRepository
import com.omasyo.comicsnac.details.Arg
import com.omasyo.comicsnac.details.Error
import com.omasyo.comicsnac.details.Loading
import com.omasyo.comicsnac.details.RefreshWrapper
import com.omasyo.comicsnac.details.Success
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.location.Location
import com.omasyo.comicsnac.model.movie.MovieDetails
import com.omasyo.comicsnac.model.`object`.ObjectItem
import com.omasyo.comicsnac.model.team.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
internal class MovieViewModel @Inject constructor(
    movieRepository: MovieRepository,
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository,
    private val objectRepository: ObjectRepository,
    private val teamRepository: TeamRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>(Arg))

    val detailsUiState =
        RefreshWrapper(viewModelScope) { movieRepository.getMovieDetails(id) }.response


    val characters: Flow<PagingData<Character>> = getPagingData {
        characterRepository.getCharactersWithId(charactersId)
    }

    val locations: Flow<PagingData<Location>> = getPagingData {
        locationRepository.getLocationsWithId(locationsId)
    }

    val objects: Flow<PagingData<ObjectItem>> = getPagingData {
        objectRepository.getObjectsWithId(objectsId)
    }

    val teams: Flow<PagingData<Team>> = getPagingData {
        teamRepository.getTeamsWithId(teamsId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T : Any> getPagingData(init: MovieDetails.() -> Flow<PagingData<T>>) =
        detailsUiState.flatMapLatest {
            when (it) {
                is Error -> emptyFlow()
                Loading -> emptyFlow()
                is Success -> {
                    init(it.content)
                }
            }
        }.cachedIn(viewModelScope)
}