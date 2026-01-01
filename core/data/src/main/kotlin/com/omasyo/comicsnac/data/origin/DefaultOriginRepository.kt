package com.omasyo.comicsnac.data.origin

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omasyo.comicsnac.data.CustomPagingSource
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.data.di.IODispatcher
import com.omasyo.comicsnac.data.fromNetworkError
import com.omasyo.comicsnac.data.settings.AuthRepository
import com.omasyo.comicsnac.model.origin.OriginBasic
import com.omasyo.comicsnac.model.origin.OriginDetails
import com.omasyo.comicsnac.network.common.models.OriginApiModel
import com.omasyo.comicsnac.network.origin.OriginNetworkSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultOriginRepository @Inject constructor(
    private val networkSource: OriginNetworkSource,
    private val authRepository: AuthRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : OriginRepository {
    override fun getOriginDetails(id: String): Flow<RepositoryResponse<OriginDetails>> =
        authRepository.getApiKey().map { apiKey ->
            networkSource.getOriginDetails(apiKey, id)
                .fold(onSuccess = { RepositoryResponse.Success(it.results.toOriginDetails()) }) {
                    fromNetworkError(it)
                }
        }.flowOn(dispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllOrigins(): Flow<PagingData<OriginBasic>> =
        authRepository.getApiKey().flatMapLatest { apiKey ->
            Pager(
                config = pagingConfig,
            ) {
                CustomPagingSource(
                    provider = { page ->
                        networkSource.getAllOrigins(
                            apiKey,
                            PageSize,
                            PageSize * page
                        ).getOrThrow().results
                    }, mapper = List<OriginApiModel>::toOrigins
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