package com.omasyo.comicsnac.network.origin

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.origin.models.OriginDetailsResponse
import com.omasyo.comicsnac.network.origin.models.OriginListResponse

interface OriginNetworkSource : NetworkSource {

    suspend fun getOriginDetails(apiKey: String, id: String): Result<OriginDetailsResponse>

    suspend fun getAllOrigins(
        apiKey: String,
        pageSize: Int,
        offset: Int
    ): Result<OriginListResponse>
}