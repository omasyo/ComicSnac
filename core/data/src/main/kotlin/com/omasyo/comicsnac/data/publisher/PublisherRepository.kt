package com.omasyo.comicsnac.data.publisher

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.publisher.Publisher
import com.omasyo.comicsnac.model.publisher.PublisherDetails
import kotlinx.coroutines.flow.Flow

interface PublisherRepository {
    fun getPublisherDetails(id: String): Flow<RepositoryResponse<PublisherDetails>>

    fun getPublisherCharactersId(id: String): Flow<RepositoryResponse<List<Int>>>

    fun getPublisherVolumesId(id: String): Flow<RepositoryResponse<List<Int>>>

    fun getPopularPublishers(): Flow<RepositoryResponse<List<Publisher>>>

    fun getAllPublishers(): Flow<PagingData<Publisher>>
}