package com.omasyo.comicsnac.ui.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.omasyo.comicsnac.model.volume.Volume
import com.omasyo.comicsnac.model.search.SearchType
import com.omasyo.comicsnac.ui.R


@Composable
fun VolumeWideCard(
    modifier: Modifier = Modifier,
    volume: Volume,
    onClick: (String) -> Unit
) = with(volume) {
    WideCard(
        modifier = modifier,
        name = name,
        description = deck,
        onClick = { onClick(apiDetailUrl) },
        imageUrl = imageUrl,
        imageDescription = stringResource(R.string.volume_image_desc, name),
        type = SearchType.Volume.name,
    )
}