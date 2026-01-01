package com.omasyo.comicsnac

import android.net.Uri
import android.util.Log
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omasyo.comicsnac.categories.categoriesNavigation
import com.omasyo.comicsnac.categories.character.navigateToCharacters
import com.omasyo.comicsnac.categories.movie.navigateToMovies
import com.omasyo.comicsnac.categories.navigateToCategories
import com.omasyo.comicsnac.categories.series.navigateToSeries
import com.omasyo.comicsnac.categories.volume.navigateToVolumes
import com.omasyo.comicsnac.details.character.characterRoute
import com.omasyo.comicsnac.details.concept.conceptRoute
import com.omasyo.comicsnac.details.episode.episodeRoute
import com.omasyo.comicsnac.details.issue.issueRoute
import com.omasyo.comicsnac.details.location.locationRoute
import com.omasyo.comicsnac.details.movie.movieRoute
import com.omasyo.comicsnac.details.`object`.objectRoute
import com.omasyo.comicsnac.details.origin.originRoute
import com.omasyo.comicsnac.details.person.personRoute
import com.omasyo.comicsnac.details.power.powerRoute
import com.omasyo.comicsnac.details.publisher.publisherRoute
import com.omasyo.comicsnac.details.series.seriesRoute
import com.omasyo.comicsnac.details.storyarc.storyArcRoute
import com.omasyo.comicsnac.details.team.teamRoute
import com.omasyo.comicsnac.details.volume.volumeRoute
import com.omasyo.comicsnac.home.HomeRoute
import com.omasyo.comicsnac.home.homeRoute
import com.omasyo.comicsnac.search.navigateToSearch
import com.omasyo.comicsnac.search.searchRoute
import com.omasyo.comicsnac.settings.AuthRoute
import com.omasyo.comicsnac.settings.authRoute
import com.omasyo.comicsnac.settings.navigateToTheme
import com.omasyo.comicsnac.settings.themeRoute
import com.omasyo.comicsnac.ui.components.placeholders.InDevelopmentPlaceholder
import com.omasyo.comicsnac.ui.components.webview.openUrl

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    apiKeyPresent: Boolean,
    navController: NavHostController = rememberNavController()
) {
    val context = LocalContext.current
    val colorScheme = MaterialTheme.colorScheme
    NavHost(
        modifier = modifier
            .semantics {
                testTagsAsResourceId = true
            },
        navController = navController,
        startDestination = if (apiKeyPresent) HomeRoute.route else AuthRoute.route,
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                spring(stiffness = Spring.StiffnessMediumLow)
            )
        },
        exitTransition =
        { fadeOut() + scaleOut(targetScale = 0.9f) },
        popEnterTransition =
        { fadeIn() + scaleIn(initialScale = 0.9f) },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                spring(stiffness = Spring.StiffnessMediumLow)
            )
        },
    ) {
        val onBackPressed: () -> Unit = { navController.popBackStack() }
        val onItemClicked = { apiUrl: String ->
            try {
                navController.navigate(
                    Uri.parse(apiUrl)
                )
            } catch (e: IllegalArgumentException) {
                Log.e("AppNavHost", "AppNavHost: ${e.message}")

                if (apiUrl.contains("api")) {
                    navController.navigate("error")
                }

                openUrl(context, apiUrl, colorScheme)

            }
        }

        homeRoute(
            onItemClicked = onItemClicked,
            onSearchClicked = { navController.navigateToSearch() },
            onMoreCategoriesClicked = { navController.navigateToCategories() },
            onSettingsClicked = { navController.navigateToTheme() },
            onCharacterCategoryClicked = { navController.navigateToCharacters() },
            onVolumeCategoryClicked = { navController.navigateToVolumes() },
            onMovieCategoryClicked = { navController.navigateToMovies() },
            onSeriesCategoryClicked = { navController.navigateToSeries() }
        )

        authRoute(
            onVerificationComplete = {
                navController.popBackStack()
                navController.navigate(HomeRoute.route)
            }
        )

        themeRoute(
            onBackPressed = onBackPressed
        )

        searchRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        characterRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        conceptRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        episodeRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        issueRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        locationRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        movieRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        objectRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        originRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        personRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        powerRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        publisherRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        seriesRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        storyArcRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        teamRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        volumeRoute(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed
        )

        categoriesNavigation(
            onItemClicked = onItemClicked,
            onBackPressed = onBackPressed,
            navController = navController
        )

        composable(
            "error",
            enterTransition = { fadeIn(spring()) + scaleIn(initialScale = 1.2f) },
            popExitTransition = {
                fadeOut(spring(stiffness = Spring.StiffnessMedium)) + scaleOut(
                    spring(stiffness = Spring.StiffnessMedium),
                    targetScale = 1.2f
                )
            }
        ) {
            Surface {
                Box {
                    InDevelopmentPlaceholder(Modifier.fillMaxSize())

                    Text(
                        "Go Back",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier
                            .padding(bottom = 64f.dp)
                            .clickable { navController.popBackStack() }
                            .background(MaterialTheme.colorScheme.tertiary)
                            .padding(16f.dp)
                            .align(Alignment.BottomCenter)
                    )
                }
            }
        }
    }
}