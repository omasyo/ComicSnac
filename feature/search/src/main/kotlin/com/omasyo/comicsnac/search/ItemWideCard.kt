package com.omasyo.comicsnac.search

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.concept.Concept
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.model.location.Location
import com.omasyo.comicsnac.model.`object`.ObjectItem
import com.omasyo.comicsnac.model.search.SearchModel
import com.omasyo.comicsnac.model.storyarc.StoryArc
import com.omasyo.comicsnac.model.volume.Volume
import com.omasyo.comicsnac.ui.components.cards.CharacterWideCard
import com.omasyo.comicsnac.ui.components.cards.ConceptWideCard
import com.omasyo.comicsnac.ui.components.cards.IssueWideCard
import com.omasyo.comicsnac.ui.components.cards.LocationWideCard
import com.omasyo.comicsnac.ui.components.cards.ObjectWideCard
import com.omasyo.comicsnac.ui.components.cards.StoryArcWideCard
import com.omasyo.comicsnac.ui.components.cards.VolumeWideCard

@Composable
fun ItemWideCard(
    modifier: Modifier = Modifier,
    item: SearchModel,
    onItemClicked: (String) -> Unit
) {
    when (item) {
        is Character -> CharacterWideCard(
            modifier = modifier,
            character = item,
            onClick = onItemClicked
        )

        is Concept -> ConceptWideCard(
            modifier = modifier,
            concept = item,
            onClick = onItemClicked
        )

        is ObjectItem -> ObjectWideCard(
            modifier = modifier,
            objectItem = item,
            onClick = onItemClicked
        )

        is Location -> LocationWideCard(
            modifier = modifier,
            location = item,
            onClick = onItemClicked
        )

        is Issue -> IssueWideCard(
            modifier = modifier,
            issue = item,
            onClick = onItemClicked
        )

        is StoryArc -> StoryArcWideCard(
            modifier = modifier,
            storyArc = item,
            onClick = onItemClicked
        )

        is Volume -> VolumeWideCard(
            modifier = modifier,
            volume = item,
            onClick = onItemClicked
        )

        else -> throw NotImplementedError("Unknown type $item")
    }
}