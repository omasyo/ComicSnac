package com.omasyo.comicsnac.categories

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.navigation
import com.omasyo.comicsnac.categories.character.characterRoute
import com.omasyo.comicsnac.categories.character.navigateToCharacters
import com.omasyo.comicsnac.categories.concept.conceptRoute
import com.omasyo.comicsnac.categories.concept.navigateToConcepts
import com.omasyo.comicsnac.categories.episode.episodeRoute
import com.omasyo.comicsnac.categories.episode.navigateToEpisodes
import com.omasyo.comicsnac.categories.issue.issueRoute
import com.omasyo.comicsnac.categories.issue.navigateToIssue
import com.omasyo.comicsnac.categories.locations.locationRoute
import com.omasyo.comicsnac.categories.locations.navigateToLocations
import com.omasyo.comicsnac.categories.movie.movieRoute
import com.omasyo.comicsnac.categories.movie.navigateToMovies
import com.omasyo.comicsnac.categories.`object`.navigateToObjects
import com.omasyo.comicsnac.categories.`object`.objectRoute
import com.omasyo.comicsnac.categories.origin.navigateToOrigins
import com.omasyo.comicsnac.categories.origin.originRoute
import com.omasyo.comicsnac.categories.person.navigateToPeople
import com.omasyo.comicsnac.categories.person.personRoute
import com.omasyo.comicsnac.categories.power.navigateToPowers
import com.omasyo.comicsnac.categories.power.powerRoute
import com.omasyo.comicsnac.categories.publisher.navigateToPublishers
import com.omasyo.comicsnac.categories.publisher.publisherRoute
import com.omasyo.comicsnac.categories.series.navigateToSeries
import com.omasyo.comicsnac.categories.series.seriesRoute
import com.omasyo.comicsnac.categories.storyarcs.navigateToStoryArcs
import com.omasyo.comicsnac.categories.storyarcs.storyArcRoute
import com.omasyo.comicsnac.categories.team.navigateToTeams
import com.omasyo.comicsnac.categories.team.teamRoute
import com.omasyo.comicsnac.categories.volume.navigateToVolumes
import com.omasyo.comicsnac.categories.volume.volumeRoute

fun NavGraphBuilder.categoriesNavigation(
    onItemClicked: (String) -> Unit,
    onBackPressed: () -> Unit,
    navController: NavController
) {
    navigation("/", CategoriesRoute.route) {
        characterRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        conceptRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        episodeRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        issueRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        locationRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        movieRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        objectRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        originRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        personRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        powerRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        publisherRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        seriesRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        storyArcRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        teamRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        volumeRoute(onItemClicked = onItemClicked, onBackPressed = onBackPressed)

        categoriesRoute(
            onCharactersClicked = { navController.navigateToCharacters() },
            onConceptsClicked = { navController.navigateToConcepts() },
            onEpisodesClicked = { navController.navigateToEpisodes() },
            onIssuesClicked = { navController.navigateToIssue() },
            onLocationsClicked = { navController.navigateToLocations() },
            onMoviesClicked = { navController.navigateToMovies() },
            onObjectsClicked = { navController.navigateToObjects() },
            onOriginsClicked = { navController.navigateToOrigins() },
            onPeopleClicked = { navController.navigateToPeople() },
            onPowersClicked = { navController.navigateToPowers() },
            onPublishersClicked = { navController.navigateToPublishers() },
            onSeriesClicked = { navController.navigateToSeries() },
            onStoryArcsClicked = { navController.navigateToStoryArcs() },
            onTeamsClicked = { navController.navigateToTeams() },
            onVolumesClicked = { navController.navigateToVolumes() },
            onBackPressed = onBackPressed
        )
    }
}