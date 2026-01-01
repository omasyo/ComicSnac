package com.omasyo.comicsnac.network.issue

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.common.Sort
import com.omasyo.comicsnac.network.issue.models.IssueDetailsResponse
import com.omasyo.comicsnac.network.issue.models.IssueListResponse

interface IssueNetworkSource : NetworkSource {
    suspend fun getIssueDetails(apiKey: String, id: String): Result<IssueDetailsResponse>

    suspend fun getRecentIssues(
        apiKey: String,
        pageSize: Int,
        offset: Int
    ): Result<IssueListResponse>

    suspend fun getAllIssues(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sortCoverDate: Sort = Sort.Descending
    ): Result<IssueListResponse>

    suspend fun getIssuesWithId(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        sortCoverDate: Sort = Sort.Descending,
        issuesId: List<Int>
    ): Result<IssueListResponse>
}