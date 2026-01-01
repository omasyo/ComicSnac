package com.omasyo.comicsnac.data.team

import com.omasyo.comicsnac.data.issue.toIssueBasic
import com.omasyo.comicsnac.data.publisher.toPublisherBasic
import com.omasyo.comicsnac.model.team.Team
import com.omasyo.comicsnac.model.team.TeamDetails
import com.omasyo.comicsnac.network.team.models.TeamDetailsApiModel
import com.omasyo.comicsnac.network.search.models.TeamListApiModel

internal fun List<TeamListApiModel>.toTeams() = map { apiModel -> apiModel.toTeam() }

internal fun TeamListApiModel.toTeam() =
    Team(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )

internal fun TeamDetailsApiModel.toTeamDetails() =
    TeamDetails(
        aliases = aliases?.split('\n') ?: emptyList(),
        characterEnemiesId = characterEnemies.map { it.id },
        characterFriendsId = characterFriends.map { it.id },
        charactersId = characters.map { it.id },
        countOfMembers = countOfTeamMembers,
        deck = deck ?: "",
        description = description ?: "",
        firstAppearedInIssue = firstAppearedInIssue.toIssueBasic(),
        id = id,
        imageUrl = image.smallUrl,
        moviesId = movies.map { it.id },
        name = name,
        publisher = publisher?.toPublisherBasic(),
        siteDetailUrl = siteDetailUrl,
        volumeCreditsId = volumeCredits.map { it.id }

    )