package com.omasyo.comicsnac.data.issue

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.Sort
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.model.issue.IssueDetails
import kotlinx.coroutines.flow.Flow

interface IssueRepository {
    fun getIssueDetails(fullId: String): Flow<RepositoryResponse<IssueDetails>>

    fun getRecentIssues(): Flow<RepositoryResponse<List<Issue>>>

    fun getAllIssues(sort: Sort = Sort.Descending): Flow<PagingData<Issue>>

    fun getIssuesWithId(issuesId: List<Int>, sort: Sort = Sort.Descending): Flow<PagingData<Issue>>
}