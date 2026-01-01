package com.omasyo.comicsnac.data.storyarc

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.storyarc.StoryArc
import com.omasyo.comicsnac.model.storyarc.StoryArcDetails
import kotlinx.coroutines.flow.Flow

interface StoryArcRepository {
    fun getStoryArcDetails(id: String): Flow<RepositoryResponse<StoryArcDetails>>

    fun getStoryArcsWithId(storyArcsId: List<Int>): Flow<PagingData<StoryArc>>

    fun getAllStoryArcs(): Flow<PagingData<StoryArc>>
}