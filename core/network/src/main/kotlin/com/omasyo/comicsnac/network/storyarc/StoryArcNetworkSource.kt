package com.omasyo.comicsnac.network.storyarc

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.storyarc.models.StoryArcDetailsResponse
import com.omasyo.comicsnac.network.storyarc.models.StoryArcListResponse

interface StoryArcNetworkSource : NetworkSource {
    suspend fun getStoryArcDetails(apiKey: String, id: String): Result<StoryArcDetailsResponse>

    suspend fun getAllStoryArcs(
        apiKey: String,
        pageSize: Int,
        offset: Int
    ): Result<StoryArcListResponse>

    suspend fun getStoryArcsWithId(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        storyArcIds: List<Int>
    ): Result<StoryArcListResponse>
}