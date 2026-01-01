package com.omasyo.comicsnac.data.issue

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.omasyo.comicsnac.data.CustomPagingSource
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.data.di.IODispatcher
import com.omasyo.comicsnac.data.fromNetworkError
import com.omasyo.comicsnac.data.settings.AuthRepository
import com.omasyo.comicsnac.model.Sort
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.model.issue.IssueDetails
import com.omasyo.comicsnac.network.issue.IssueNetworkSource
import com.omasyo.comicsnac.network.search.models.IssueListApiModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import com.omasyo.comicsnac.network.common.Sort as NetworkSort

@OptIn(ExperimentalCoroutinesApi::class)
internal class DefaultIssueRepository @Inject constructor(
    private val networkSource: IssueNetworkSource,
    private val authRepository: AuthRepository,
    @IODispatcher private val dispatcher: CoroutineDispatcher
) : IssueRepository {
    override fun getIssueDetails(fullId: String): Flow<RepositoryResponse<IssueDetails>> =
        authRepository.getApiKey().map { apiKey ->
            networkSource.getIssueDetails(apiKey, fullId)
                .fold(onSuccess = { RepositoryResponse.Success(it.results.toIssueDetails()) }) {
                    fromNetworkError(it)
                }
        }.flowOn(dispatcher)

    override fun getRecentIssues(): Flow<RepositoryResponse<List<Issue>>> =
        authRepository.getApiKey().map { apiKey ->
            networkSource.getRecentIssues(apiKey, 10, 0)
                .fold(onSuccess = { RepositoryResponse.Success(it.results.toIssues()) }) {
                    fromNetworkError(it)
                }
        }.flowOn(dispatcher)

    override fun getAllIssues(sort: Sort): Flow<PagingData<Issue>> =
        authRepository.getApiKey().flatMapLatest { apiKey ->
            Pager(
                config = pagingConfig,
            ) {
                CustomPagingSource(
                    provider = { page ->
                        networkSource.getAllIssues(
                            apiKey,
                            PageSize,
                            PageSize * page,
                            NetworkSort.valueOf(sort.name),
                        ).getOrThrow().results
                    }, mapper = List<IssueListApiModel>::toIssues
                )
            }.flow
        }.flowOn(dispatcher)

    override fun getIssuesWithId(issuesId: List<Int>, sort: Sort): Flow<PagingData<Issue>> =
        authRepository.getApiKey().flatMapLatest { apiKey ->
            Pager(
                config = pagingConfig,
            ) {
                CustomPagingSource(
                    provider = { page ->
                        networkSource.getIssuesWithId(
                            apiKey,
                            PageSize,
                            PageSize * page,
                            NetworkSort.valueOf(sort.name),
                            issuesId
                        ).getOrThrow().results
                    }, mapper = List<IssueListApiModel>::toIssues
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