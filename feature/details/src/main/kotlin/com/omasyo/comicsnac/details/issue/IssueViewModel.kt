package com.omasyo.comicsnac.details.issue

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omasyo.comicsnac.data.character.CharacterRepository
import com.omasyo.comicsnac.data.issue.IssueRepository
import com.omasyo.comicsnac.data.location.LocationRepository
import com.omasyo.comicsnac.data.`object`.ObjectRepository
import com.omasyo.comicsnac.data.storyarc.StoryArcRepository
import com.omasyo.comicsnac.data.team.TeamRepository
import com.omasyo.comicsnac.details.Arg
import com.omasyo.comicsnac.details.Error
import com.omasyo.comicsnac.details.Loading
import com.omasyo.comicsnac.details.RefreshWrapper
import com.omasyo.comicsnac.details.Success
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.issue.IssueDetails
import com.omasyo.comicsnac.model.location.Location
import com.omasyo.comicsnac.model.`object`.ObjectItem
import com.omasyo.comicsnac.model.storyarc.StoryArc
import com.omasyo.comicsnac.model.team.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
internal class IssueViewModel @Inject constructor(
    issueRepository: IssueRepository,
    private val characterRepository: CharacterRepository,
    private val locationRepository: LocationRepository,
    private val objectRepository: ObjectRepository,
    private val storyArcRepository: StoryArcRepository,
    private val teamRepository: TeamRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>(Arg))

    val detailsUiState =
        RefreshWrapper(viewModelScope) { issueRepository.getIssueDetails(id) }.response


    val characters: Flow<PagingData<Character>> = getPagingData {
        characterRepository.getCharactersWithId(charactersId)
    }

    val locations: Flow<PagingData<Location>> = getPagingData {
        locationRepository.getLocationsWithId(locationsId)
    }

    val objects: Flow<PagingData<ObjectItem>> = getPagingData {
        objectRepository.getObjectsWithId(objectsId)
    }

    val storyArcs: Flow<PagingData<StoryArc>> = getPagingData {
        storyArcRepository.getStoryArcsWithId(storyArcsId)
    }

    val teams: Flow<PagingData<Team>> = getPagingData {
        teamRepository.getTeamsWithId(teamsId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T : Any> getPagingData(init: IssueDetails.() -> Flow<PagingData<T>>) =
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