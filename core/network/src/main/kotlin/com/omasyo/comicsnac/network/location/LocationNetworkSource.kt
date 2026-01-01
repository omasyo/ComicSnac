package com.omasyo.comicsnac.network.location

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.common.Sort
import com.omasyo.comicsnac.network.location.models.LocationDetailsResponse
import com.omasyo.comicsnac.network.location.models.LocationListResponse

interface LocationNetworkSource : NetworkSource {
    suspend fun getLocationDetails(apiKey: String, id: String): Result<LocationDetailsResponse>

    suspend fun getAllLocations(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sortCoverDate: Sort = Sort.Descending
    ): Result<LocationListResponse>

    suspend fun getLocationsWithId(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sort: Sort = Sort.Descending,
        locationsId: List<Int>
    ): Result<LocationListResponse>
}