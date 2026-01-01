package com.omasyo.comicsnac.network.episode

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.common.Sort
import com.omasyo.comicsnac.network.episode.models.EpisodeDetailsResponse
import com.omasyo.comicsnac.network.episode.models.EpisodeListResponse

interface EpisodeNetworkSource : NetworkSource {
    suspend fun getEpisodeDetails(apiKey: String, id: String): Result<EpisodeDetailsResponse>

    suspend fun getAllEpisodes(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sortAirDate: Sort = Sort.Descending
    ): Result<EpisodeListResponse>

    suspend fun getEpisodesWithId(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        episodesId: List<Int>,
        sortAirDate: Sort = Sort.Descending
    ): Result<EpisodeListResponse>
}