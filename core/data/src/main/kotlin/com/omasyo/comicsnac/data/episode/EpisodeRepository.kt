package com.omasyo.comicsnac.data.episode

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.episode.Episode
import com.omasyo.comicsnac.model.episode.EpisodeDetails
import kotlinx.coroutines.flow.Flow

interface EpisodeRepository {
    fun getEpisodeDetails(id: String): Flow<RepositoryResponse<EpisodeDetails>>

    fun getEpisodesWithId(episodesId: List<Int>): Flow<PagingData<Episode>>

    fun getAllEpisodes(): Flow<PagingData<Episode>>
}