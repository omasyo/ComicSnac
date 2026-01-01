package com.omasyo.comicsnac.network.team

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.team.models.TeamDetailsResponse
import com.omasyo.comicsnac.network.team.models.TeamListResponse

interface TeamNetworkSource : NetworkSource {
    suspend fun getTeamDetails(apiKey: String, id: String): Result<TeamDetailsResponse>

    suspend fun getAllTeams(
        apiKey: String,
        pageSize: Int,
        offset: Int
    ): Result<TeamListResponse>

    suspend fun getTeamsWithId(
        apiKey: String,
        pageSize: Int,
        offset: Int,
        teamIds: List<Int>
    ): Result<TeamListResponse>
}