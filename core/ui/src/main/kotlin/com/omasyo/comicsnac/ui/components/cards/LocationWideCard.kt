package com.omasyo.comicsnac.ui.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.omasyo.comicsnac.model.location.Location
import com.omasyo.comicsnac.model.search.SearchType
import com.omasyo.comicsnac.ui.R


@Composable
fun LocationWideCard(
    modifier: Modifier = Modifier,
    location: Location,
    onClick: (String) -> Unit
) = with(location) {
    WideCard(
        modifier = modifier,
        name = name,
        description = deck,
        onClick = { onClick(apiDetailUrl) },
        imageUrl = imageUrl,
        imageDescription = stringResource(R.string.location_image_desc, name),
        type = SearchType.Location.name,
    )
}