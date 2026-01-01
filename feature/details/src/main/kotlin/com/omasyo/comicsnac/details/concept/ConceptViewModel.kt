package com.omasyo.comicsnac.details.concept

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.omasyo.comicsnac.data.concept.ConceptRepository
import com.omasyo.comicsnac.data.issue.IssueRepository
import com.omasyo.comicsnac.data.volume.VolumeRepository
import com.omasyo.comicsnac.details.Arg
import com.omasyo.comicsnac.details.Error
import com.omasyo.comicsnac.details.Loading
import com.omasyo.comicsnac.details.RefreshWrapper
import com.omasyo.comicsnac.details.Success
import com.omasyo.comicsnac.model.concept.ConceptDetails
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.model.volume.Volume
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
internal class ConceptViewModel @Inject constructor(
    conceptRepository: ConceptRepository,
    private val issueRepository: IssueRepository,
    private val volumeRepository: VolumeRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>(Arg))

    val detailsUiState =
        RefreshWrapper(viewModelScope) { conceptRepository.getConceptDetails(id) }.response


    val issues: Flow<PagingData<Issue>> = getPagingData {
        issueRepository.getIssuesWithId(issuesId)
    }

    val volumes: Flow<PagingData<Volume>> = getPagingData {
        volumeRepository.getVolumesWithId(volumesId)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun <T : Any> getPagingData(init: ConceptDetails.() -> Flow<PagingData<T>>) =
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