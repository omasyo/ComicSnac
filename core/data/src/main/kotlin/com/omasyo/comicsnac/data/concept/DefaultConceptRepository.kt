package com.omasyo.comicsnac.data.concept

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omasyo.comicsnac.data.CustomPagingSource
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.data.di.IODispatcher
import com.omasyo.comicsnac.data.fromNetworkError
import com.omasyo.comicsnac.data.settings.AuthRepository
import com.omasyo.comicsnac.model.concept.Concept
import com.omasyo.comicsnac.model.concept.ConceptDetails
import com.omasyo.comicsnac.network.concept.ConceptNetworkSource
import com.omasyo.comicsnac.network.search.models.ConceptListApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class DefaultConceptRepository @Inject constructor(
    private val networkSource: ConceptNetworkSource,
    private val authRepository: AuthRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : ConceptRepository {
    override fun getConceptDetails(id: String): Flow<RepositoryResponse<ConceptDetails>> =
        authRepository.getApiKey().map { apiKey ->
            networkSource.getConceptDetails(apiKey, id)
                .fold(onSuccess = { RepositoryResponse.Success(it.results.toConceptDetails()) }) {
                    fromNetworkError(it)
                }
        }.flowOn(dispatcher)

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getAllConcepts(): Flow<PagingData<Concept>> =
        authRepository.getApiKey().flatMapLatest { apiKey ->
            Pager(
                config = pagingConfig,
            ) {
                CustomPagingSource(
                    provider = { page ->
                        networkSource.getAllConcepts(
                            apiKey,
                            PageSize,
                            PageSize * page
                        ).getOrThrow().results
                    }, mapper = List<ConceptListApiModel>::toConcepts
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