package com.omasyo.comicsnac.ui.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.omasyo.comicsnac.model.team.Team
import com.omasyo.comicsnac.ui.R


@Composable
fun TeamWideCard(
    modifier: Modifier = Modifier,
    team: Team,
    onClick: (String) -> Unit
) {
    WideCard(
        name = team.name,
        description = team.deck,
        onClick = { onClick(team.apiDetailUrl) },
        imageUrl = team.imageUrl,
        type = "",
        imageDescription = stringResource(
            R.string.team_image_desc, team.name
        ),
        modifier = modifier,
    )
}