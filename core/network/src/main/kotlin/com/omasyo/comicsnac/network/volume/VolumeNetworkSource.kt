package com.omasyo.comicsnac.network.volume

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.volume.models.VolumeDetailsResponse
import com.omasyo.comicsnac.network.volume.models.VolumeListResponse

interface VolumeNetworkSource : NetworkSource {
    suspend fun getVolumeDetails(apiKey: String, id: String): Result<VolumeDetailsResponse>

    suspend fun getAllVolumes(
        apiKey: String,
        pageSize: Int,
        offset: Int
    ): Result<VolumeListResponse>

    suspend fun getVolumesWithId(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        volumeIds: List<Int>
    ): Result<VolumeListResponse>
}