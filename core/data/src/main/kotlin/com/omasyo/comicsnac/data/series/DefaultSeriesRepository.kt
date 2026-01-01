package com.omasyo.comicsnac.data.series

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omasyo.comicsnac.data.CustomPagingSource
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.data.di.IODispatcher
import com.omasyo.comicsnac.data.fromNetworkError
import com.omasyo.comicsnac.data.settings.AuthRepository
import com.omasyo.comicsnac.model.series.Series
import com.omasyo.comicsnac.model.series.SeriesDetails
import com.omasyo.comicsnac.network.series.SeriesNetworkSource
import com.omasyo.comicsnac.network.series.models.SeriesListApiModel
import com.omasyo.comicsnac.network.series.models.SeriesListResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
internal class DefaultSeriesRepository @Inject constructor(
    private val networkSource: SeriesNetworkSource,
    private val authRepository: AuthRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : SeriesRepository {
    override fun getSeriesDetails(id: String): Flow<RepositoryResponse<SeriesDetails>> =
        authRepository.getApiKey().map { apiKey ->
            networkSource.getSeriesDetails(apiKey, id)
                .fold(onSuccess = { RepositoryResponse.Success(it.results.toSeriesDetails()) }) {
                    fromNetworkError(it)
                }
        }.flowOn(dispatcher)

    override fun getRecentSeries(): Flow<RepositoryResponse<List<Series>>> =
        authRepository.getApiKey().map { apiKey ->
            networkSource.getAllSeries(apiKey, 25, 0)
                .fold(onSuccess = { RepositoryResponse.Success(it.results.toSeries()) }) {
                    fromNetworkError(it)
                }
        }.flowOn(dispatcher)

    override fun getAllSeries(): Flow<PagingData<Series>> =
        getPagingData { apiKey, page ->
            networkSource.getAllSeries(
                apiKey,
                PageSize,
                PageSize * page
            )
        }

    override fun getSeriesWithId(seriesId: List<Int>): Flow<PagingData<Series>> =
        getPagingData { apiKey, page ->
            networkSource.getSeriesWithId(
                apiKey,
                PageSize,
                PageSize * page,
                seriesId = seriesId
            )
        }

    private fun getPagingData(init: suspend (apiKey: String, page: Int) -> Result<SeriesListResponse>): Flow<PagingData<Series>> =
        authRepository.getApiKey().flatMapLatest { apiKey ->
            Pager(
                config = pagingConfig,
            ) {
                CustomPagingSource(
                    provider = { page ->
                        init(apiKey, page).getOrThrow().results
                    },
                    mapper = List<SeriesListApiModel>::toSeries
                )
            }.flow
        }.flowOn(dispatcher)


    companion object {
        private const val PageSize = 25

        private val pagingConfig = PagingConfig(
            pageSize = PageSize, enablePlaceholders = false
        )
    }
}