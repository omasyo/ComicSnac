package com.omasyo.comicsnac.ui.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.omasyo.comicsnac.model.publisher.Publisher
import com.omasyo.comicsnac.ui.R


@Composable
fun PublisherWideCard(
    modifier: Modifier = Modifier,
    publisher: Publisher,
    onClick: (String) -> Unit
) {
    WideCard(
        name = publisher.name,
        description = publisher.deck,
        onClick = { onClick(publisher.apiDetailUrl) },
        imageUrl = publisher.imageUrl,
        type = "",
        imageDescription = stringResource(
            R.string.publisher_image_desc, publisher.name,
        ),
        modifier = modifier,
    )
}