package com.omasyo.comicsnac.data.series

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.series.Series
import com.omasyo.comicsnac.model.series.SeriesDetails
import kotlinx.coroutines.flow.Flow

interface SeriesRepository {

    fun getSeriesDetails(id: String): Flow<RepositoryResponse<SeriesDetails>>

    fun getRecentSeries(): Flow<RepositoryResponse<List<Series>>>

    fun getAllSeries(): Flow<PagingData<Series>>

    fun getSeriesWithId(seriesId: List<Int>): Flow<PagingData<Series>>
}