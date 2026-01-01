package com.omasyo.comicsnac.details.storyarc

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omasyo.comicsnac.data.episode.EpisodeRepository
import com.omasyo.comicsnac.data.issue.IssueRepository
import com.omasyo.comicsnac.data.storyarc.StoryArcRepository
import com.omasyo.comicsnac.details.Arg
import com.omasyo.comicsnac.details.Error
import com.omasyo.comicsnac.details.Loading
import com.omasyo.comicsnac.details.RefreshWrapper
import com.omasyo.comicsnac.details.Success
import com.omasyo.comicsnac.model.episode.Episode
import com.omasyo.comicsnac.model.issue.Issue
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
internal class StoryArcViewModel @Inject constructor(
    storyArcRepository: StoryArcRepository,
    private val episodeRepository: EpisodeRepository,
    private val issueRepository: IssueRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>(Arg))

    val detailsUiState =
        RefreshWrapper(viewModelScope) { storyArcRepository.getStoryArcDetails(id) }.response

    val episodes: Flow<PagingData<Episode>> = detailsUiState.flatMapLatest {
        when (it) {
            is Error -> emptyFlow()
            Loading -> emptyFlow()
            is Success -> {
                episodeRepository.getEpisodesWithId(it.content.episodesId)
            }
        }
    }.cachedIn(viewModelScope)

    val issues: Flow<PagingData<Issue>> = detailsUiState.flatMapLatest {
        when (it) {
            is Error -> emptyFlow()
            Loading -> emptyFlow()
            is Success -> {
                issueRepository.getIssuesWithId(it.content.issuesId)
            }
        }
    }.cachedIn(viewModelScope)
}