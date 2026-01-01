package com.omasyo.comicsnac.network.power

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.power.models.PowerDetailsResponse
import com.omasyo.comicsnac.network.power.models.PowerListResponse

interface PowerNetworkSource : NetworkSource {

    suspend fun getPowerDetails(apiKey: String, id: String): Result<PowerDetailsResponse>

    suspend fun getAllPowers(
        apiKey: String,
        pageSize: Int,
        offset: Int
    ): Result<PowerListResponse>
}