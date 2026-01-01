package com.omasyo.comicsnac.data.power

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.power.PowerBasic
import com.omasyo.comicsnac.model.power.PowerDetails
import kotlinx.coroutines.flow.Flow

interface PowerRepository {
    fun getPowerDetails(id: String): Flow<RepositoryResponse<PowerDetails>>

    fun getAllPowers(): Flow<PagingData<PowerBasic>>
}