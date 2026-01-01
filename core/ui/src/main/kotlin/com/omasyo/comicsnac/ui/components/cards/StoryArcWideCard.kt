package com.omasyo.comicsnac.ui.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.omasyo.comicsnac.model.search.SearchType
import com.omasyo.comicsnac.model.storyarc.StoryArc
import com.omasyo.comicsnac.ui.R


@Composable
fun StoryArcWideCard(
    modifier: Modifier = Modifier,
    storyArc: StoryArc,
    onClick: (String) -> Unit
) = with(storyArc) {
    WideCard(
        modifier = modifier,
        name = name,
        description = deck,
        onClick = { onClick(apiDetailUrl) },
        imageUrl = imageUrl,
        imageDescription = stringResource(R.string.story_arc_image_desc, name),
        type = SearchType.StoryArc.name,
    )
}