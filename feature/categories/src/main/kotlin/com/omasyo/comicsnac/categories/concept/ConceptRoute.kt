package com.omasyo.comicsnac.categories.concept

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.omasyo.comicsnac.categories.CategoryScreen
import com.omasyo.comicsnac.model.NavigationRoute
import com.omasyo.comicsnac.ui.R
import com.omasyo.comicsnac.ui.components.cards.ComicCard
import com.omasyo.comicsnac.ui.components.cards.WideCard

private object ConceptRoute : NavigationRoute("concepts")

internal fun NavGraphBuilder.conceptRoute(
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit,
    onBackPressed: () -> Unit
) = composable(ConceptRoute.route) {
    ConceptRoute(
        modifier = modifier,
        onItemClicked = onItemClicked,
        onBackPressed = onBackPressed
    )
}

fun NavController.navigateToConcepts(navOptions: NavOptions? = null) =
    navigate(ConceptRoute.route, navOptions)

@Composable
internal fun ConceptRoute(
    modifier: Modifier = Modifier,
    onItemClicked: (String) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: ConceptViewModel = hiltViewModel()
) {
    CategoryScreen(
        modifier = modifier,
        title = stringResource(R.string.concepts),
        onBackPressed = onBackPressed,
        layoutType = viewModel.layoutType.collectAsState().value,
        onToggleLayoutType = viewModel::onToggleLayout,
        items = viewModel.items.collectAsLazyPagingItems(),
        listContentBuilder = { concept ->
            WideCard(
                name = concept.name,
                description = concept.deck,
                onClick = { onItemClicked(concept.apiDetailUrl) },
                imageUrl = concept.imageUrl,
                type = "",
                imageDescription = stringResource(
                    R.string.concept_image_desc, concept.name
                )
            )
        }
    ) { concept ->
        ComicCard(
            modifier = Modifier.aspectRatio(0.5f),
            name = concept.name,
            imageUrl = concept.imageUrl,
            contentDescription = stringResource(
                R.string.concept_image_desc, concept.name
            ),
            onClick = { onItemClicked(concept.apiDetailUrl) }
        )
    }
}