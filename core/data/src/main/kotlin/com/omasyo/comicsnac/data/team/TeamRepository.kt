package com.omasyo.comicsnac.data.team

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.team.Team
import com.omasyo.comicsnac.model.team.TeamDetails
import kotlinx.coroutines.flow.Flow

interface TeamRepository {

    fun getTeamDetails(id: String): Flow<RepositoryResponse<TeamDetails>>

    fun getAllTeams(): Flow<PagingData<Team>>

    fun getTeamsWithId(teamsId: List<Int>): Flow<PagingData<Team>>
}