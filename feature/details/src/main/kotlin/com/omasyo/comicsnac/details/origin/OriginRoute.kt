package com.omasyo.comicsnac.details.origin

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.omasyo.comicsnac.details.Arg
import com.omasyo.comicsnac.details.DetailsNavigationRoute

private object OriginRoute : DetailsNavigationRoute("origin", "4030") {
    override val requiredArguments: List<String> = listOf(Arg)
}

fun NavGraphBuilder.originRoute(
    modifier: Modifier = Modifier,
    onItemClicked: (fullId: String) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = OriginRoute.route,
    deepLinks = OriginRoute.deepLinks
) {
    OriginRoute(
        modifier = modifier,
        onItemClicked = onItemClicked,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun OriginRoute(
    modifier: Modifier = Modifier,
    onItemClicked: (fullId: String) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: OriginViewModel = hiltViewModel()
) {
    OriginDetailsScreen(
        modifier = modifier,
        onItemClicked = onItemClicked,
        onBackPressed = onBackPressed,
        detailsUiState = viewModel.detailsUiState.collectAsState().value,
        characters = viewModel.characters.collectAsLazyPagingItems(),
    )
}