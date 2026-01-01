package com.omasyo.comicsnac.data.volume

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.volume.Volume
import com.omasyo.comicsnac.model.volume.VolumeDetails
import kotlinx.coroutines.flow.Flow

interface VolumeRepository {

    fun getVolumeDetails(id: String): Flow<RepositoryResponse<VolumeDetails>>

    fun getRecentVolumes(): Flow<RepositoryResponse<List<Volume>>>

    fun getAllVolumes(): Flow<PagingData<Volume>>

    fun getVolumesWithId(volumesId: List<Int>): Flow<PagingData<Volume>>
}