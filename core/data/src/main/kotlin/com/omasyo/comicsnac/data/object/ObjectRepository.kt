package com.omasyo.comicsnac.data.`object`

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.`object`.ObjectDetails
import com.omasyo.comicsnac.model.`object`.ObjectItem
import kotlinx.coroutines.flow.Flow

interface ObjectRepository {

    fun getObjectDetails(id: String): Flow<RepositoryResponse<ObjectDetails>>

    fun getAllObjects(): Flow<PagingData<ObjectItem>>

    fun getObjectsWithId(objectsId: List<Int>): Flow<PagingData<ObjectItem>>
}