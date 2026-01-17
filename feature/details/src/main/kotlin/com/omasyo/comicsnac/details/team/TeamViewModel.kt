package com.omasyo.comicsnac.details.team

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omasyo.comicsnac.data.character.CharacterRepository
import com.omasyo.comicsnac.data.movie.MovieRepository
import com.omasyo.comicsnac.data.team.TeamRepository
import com.omasyo.comicsnac.data.volume.VolumeRepository
import com.omasyo.comicsnac.details.Arg
import com.omasyo.comicsnac.details.Error
import com.omasyo.comicsnac.details.Loading
import com.omasyo.comicsnac.details.RefreshWrapper
import com.omasyo.comicsnac.details.Success
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.movie.Movie
import com.omasyo.comicsnac.model.team.TeamDetails
import com.omasyo.comicsnac.model.volume.Volume
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
internal class TeamViewModel @Inject constructor(
    teamRepository: TeamRepository,
    private val characterRepository: CharacterRepository,
    private val movieRepository: MovieRepository,
    private val volumeRepository: VolumeRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>(Arg))

    val detailsUiState =
        RefreshWrapper(viewModelScope) { teamRepository.getTeamDetails(id) }.response


    val characters: Flow<PagingData<Character>> = getPagingData {
        characterRepository.getCharactersWithId(charactersId)
    }

    val characterEnemies: Flow<PagingData<Character>> = getPagingData {
        characterRepository.getCharactersWithId(characterEnemiesId)
    }

    val characterFriends: Flow<PagingData<Character>> = getPagingData {
        characterRepository.getCharactersWithId(characterFriendsId)
    }

    val movies: Flow<PagingData<Movie>> = getPagingData {
        movieRepository.getMoviesWithId(moviesId)
    }

    val volumes: Flow<PagingData<Volume>> = getPagingData {
        volumeRepository.getVolumesWithId(volumeCreditsId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T : Any> getPagingData(init: TeamDetails.() -> Flow<PagingData<T>>) =
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