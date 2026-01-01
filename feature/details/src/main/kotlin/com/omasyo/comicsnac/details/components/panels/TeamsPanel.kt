package com.omasyo.comicsnac.details.components.panels

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.omasyo.comicsnac.details.R
import com.omasyo.comicsnac.details.components.DetailsGrid
import com.omasyo.comicsnac.model.team.Team
import com.omasyo.comicsnac.ui.components.cards.ComicCard
import com.omasyo.comicsnac.ui.components.lazylist.PanelLazyListScope
import com.omasyo.comicsnac.ui.R.string as CommonString

internal fun PanelLazyListScope.teamsPanel(
    items: LazyPagingItems<Team>,
    expandedProvider: (Int) -> Boolean,
    onToggleExpand: (Int) -> Unit,
    onItemClicked: (String) -> Unit
) = teamsPanel(
    CommonString.teams,
    items,
    expandedProvider,
    onToggleExpand,
    onItemClicked
)

internal fun PanelLazyListScope.teamFriendsPanel(
    items: LazyPagingItems<Team>,
    expandedProvider: (Int) -> Boolean,
    onToggleExpand: (Int) -> Unit,
    onItemClicked: (String) -> Unit
) = teamsPanel(
    R.string.team_friends,
    items,
    expandedProvider,
    onToggleExpand,
    onItemClicked
)

internal fun PanelLazyListScope.teamEnemiesPanel(
    items: LazyPagingItems<Team>,
    expandedProvider: (Int) -> Boolean,
    onToggleExpand: (Int) -> Unit,
    onItemClicked: (String) -> Unit
) = teamsPanel(
    R.string.team_enemies,
    items,
    expandedProvider,
    onToggleExpand,
    onItemClicked
)

private fun PanelLazyListScope.teamsPanel(
    @StringRes nameResId: Int,
    items: LazyPagingItems<Team>,
    expandedProvider: (Int) -> Boolean,
    onToggleExpand: (Int) -> Unit,
    onItemClicked: (String) -> Unit
) {
    panel { index ->
        DetailsGrid(name = stringResource(nameResId),
            items = items,
            expanded = expandedProvider(index),
            onToggleExpand = {
                onToggleExpand(index)
            },
            key = { it.id }) { team ->
            ComicCard(
                modifier = Modifier.width(136f.dp),
                name = team.name,
                imageUrl = team.imageUrl,
                contentDescription = stringResource(
                    CommonString.team_image_desc, team.name
                ),
                onClick = { onItemClicked(team.apiDetailUrl) })
        }
    }
}