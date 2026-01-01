package com.omasyo.comicsnac.data.origin

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.origin.OriginBasic
import com.omasyo.comicsnac.model.origin.OriginDetails
import kotlinx.coroutines.flow.Flow

interface OriginRepository {
    fun getOriginDetails(id: String): Flow<RepositoryResponse<OriginDetails>>

    fun getAllOrigins(): Flow<PagingData<OriginBasic>>
}