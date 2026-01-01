package com.omasyo.comicsnac.ui.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.search.SearchType
import com.omasyo.comicsnac.ui.R


@Composable
fun CharacterWideCard(
    modifier: Modifier = Modifier,
    character: Character,
    onClick: (String) -> Unit
) = with(character) {
    WideCard(
        modifier = modifier,
        name = name,
        description = deck,
        onClick = { onClick(apiDetailUrl) },
        imageUrl = imageUrl,
        imageDescription = stringResource(R.string.character_image_desc, name),
        type = SearchType.Character.name,
    )
}