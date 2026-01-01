package com.omasyo.comicsnac.data.location

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.location.Location
import com.omasyo.comicsnac.model.location.LocationDetails
import kotlinx.coroutines.flow.Flow

interface LocationRepository {

    fun getLocationDetails(id: String): Flow<RepositoryResponse<LocationDetails>>

    fun getAllLocations(): Flow<PagingData<Location>>

    fun getLocationsWithId(locationsId: List<Int>): Flow<PagingData<Location>>
}