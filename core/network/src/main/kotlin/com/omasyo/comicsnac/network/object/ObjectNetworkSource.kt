package com.omasyo.comicsnac.network.`object`

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.common.Sort
import com.omasyo.comicsnac.network.`object`.models.ObjectDetailsResponse
import com.omasyo.comicsnac.network.`object`.models.ObjectListResponse

interface ObjectNetworkSource : NetworkSource {
    suspend fun getObjectDetails(apiKey: String, id: String): Result<ObjectDetailsResponse>

    suspend fun getAllObjects(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sortCoverDate: Sort = Sort.Descending
    ): Result<ObjectListResponse>

    suspend fun getObjectsWithId(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sort: Sort = Sort.Descending,
        objectsId: List<Int>
    ): Result<ObjectListResponse>
}