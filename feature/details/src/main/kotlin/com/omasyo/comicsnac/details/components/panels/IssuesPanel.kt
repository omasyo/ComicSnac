package com.omasyo.comicsnac.details.components.panels

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.omasyo.comicsnac.details.components.DetailsGrid
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.ui.R
import com.omasyo.comicsnac.ui.components.cards.PlainCard
import com.omasyo.comicsnac.ui.components.lazylist.PanelLazyListScope

internal fun PanelLazyListScope.issuesPanel(
    items: LazyPagingItems<Issue>,
    expandedProvider: (Int) -> Boolean,
    onToggleExpand: (Int) -> Unit,
    onItemClicked: (String) -> Unit
) {

    panel { index ->
        DetailsGrid(
            name = stringResource(R.string.issues),
            items = items,
            expanded = expandedProvider(index),
            onToggleExpand = {
                onToggleExpand(index)
            },
            key = { it.id }
        ) { issue ->
            PlainCard(
                modifier = Modifier.width(136f.dp),
                name = issue.name,
                imageUrl = issue.imageUrl,
                contentDescription = stringResource(
                    R.string.volume_image_desc, issue.issueNumber, issue.volumeName, issue.name
                ),
                onClick = { onItemClicked(issue.apiDetailUrl) })
        }
    }
}