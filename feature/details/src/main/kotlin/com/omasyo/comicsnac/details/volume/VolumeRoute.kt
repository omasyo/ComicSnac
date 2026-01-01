package com.omasyo.comicsnac.details.volume

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.omasyo.comicsnac.details.Arg
import com.omasyo.comicsnac.details.DetailsNavigationRoute

private object VolumeRoute : DetailsNavigationRoute("volume", "4050") {
    override val requiredArguments: List<String> = listOf(Arg)
}

fun NavGraphBuilder.volumeRoute(
    modifier: Modifier = Modifier,
    onItemClicked: (fullId: String) -> Unit,
    onBackPressed: () -> Unit,
) = composable(
    route = VolumeRoute.route,
    deepLinks = VolumeRoute.deepLinks
) {
    VolumeRoute(
        modifier = modifier,
        onItemClicked = onItemClicked,
        onBackPressed = onBackPressed
    )
}

@Composable
private fun VolumeRoute(
    modifier: Modifier = Modifier,
    onItemClicked: (fullId: String) -> Unit,
    onBackPressed: () -> Unit,
    viewModel: VolumeViewModel = hiltViewModel()
) {
    VolumeDetailsScreen(
        modifier = modifier,
        onItemClicked = onItemClicked,
        onBackPressed = onBackPressed,
        detailsUiState = viewModel.detailsUiState.collectAsState().value,
        issues = viewModel.issues.collectAsLazyPagingItems()
    )
}