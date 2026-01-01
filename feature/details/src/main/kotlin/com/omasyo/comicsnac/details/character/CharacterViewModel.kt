package com.omasyo.comicsnac.details.character

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
import com.omasyo.comicsnac.model.character.CharacterDetails
import com.omasyo.comicsnac.model.movie.Movie
import com.omasyo.comicsnac.model.team.Team
import com.omasyo.comicsnac.model.volume.Volume
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
internal class CharacterViewModel @Inject constructor(
    private val characterRepository: CharacterRepository,
    private val movieRepository: MovieRepository,
    private val teamRepository: TeamRepository,
    private val volumeRepository: VolumeRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>(Arg))

    val detailsUiState =
        RefreshWrapper(viewModelScope) { characterRepository.getCharacterDetails(id) }.response


    val enemies: Flow<PagingData<Character>> = getPagingData {
        characterRepository.getCharactersWithId(enemiesId)
    }

    val friends: Flow<PagingData<Character>> = getPagingData {
        characterRepository.getCharactersWithId(friendsId)
    }

    val movies: Flow<PagingData<Movie>> = getPagingData {
        movieRepository.getMoviesWithId(moviesId)
    }

    val teams: Flow<PagingData<Team>> = getPagingData {
        teamRepository.getTeamsWithId(teamsId)
    }

    val teamEnemies: Flow<PagingData<Team>> = getPagingData {
        teamRepository.getTeamsWithId(teamEnemiesId)
    }

    val teamFriends: Flow<PagingData<Team>> = getPagingData {
        teamRepository.getTeamsWithId(teamFriendsId)
    }

    val volumes: Flow<PagingData<Volume>> = getPagingData {
        volumeRepository.getVolumesWithId(volumeCreditsId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T : Any> getPagingData(init: CharacterDetails.() -> Flow<PagingData<T>>) =
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
