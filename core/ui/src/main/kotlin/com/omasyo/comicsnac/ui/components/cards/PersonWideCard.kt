package com.omasyo.comicsnac.ui.components.cards

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.omasyo.comicsnac.model.person.Person
import com.omasyo.comicsnac.model.search.SearchType
import com.omasyo.comicsnac.ui.R


@Composable
fun PersonWideCard(
    modifier: Modifier = Modifier,
    person: Person,
    onClick: (String) -> Unit
) {
    WideCard(
        name = person.name,
        description = person.deck,
        onClick = { onClick(person.apiDetailUrl) },
        imageUrl = person.imageUrl,
        type = SearchType.Person.name,
        imageDescription = stringResource(
            R.string.person_image_desc, person.name
        ),
        modifier = modifier,
    )
}