package com.omasyo.comicsnac.details.character

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.omasyo.comicsnac.details.DetailsUiState
import com.omasyo.comicsnac.details.Domain
import com.omasyo.comicsnac.details.Error
import com.omasyo.comicsnac.details.Loading
import com.omasyo.comicsnac.details.R
import com.omasyo.comicsnac.details.Success
import com.omasyo.comicsnac.details.components.DetailsErrorPlaceholder
import com.omasyo.comicsnac.details.components.DetailsFlow
import com.omasyo.comicsnac.details.components.DetailsLoadingPlaceholder
import com.omasyo.comicsnac.details.components.DetailsScreen
import com.omasyo.comicsnac.details.components.Image
import com.omasyo.comicsnac.details.components.Info
import com.omasyo.comicsnac.details.components.panels.enemiesPanel
import com.omasyo.comicsnac.details.components.panels.friendsPanel
import com.omasyo.comicsnac.details.components.panels.moviesPanel
import com.omasyo.comicsnac.details.components.panels.teamEnemiesPanel
import com.omasyo.comicsnac.details.components.panels.teamFriendsPanel
import com.omasyo.comicsnac.details.components.panels.teamsPanel
import com.omasyo.comicsnac.details.components.panels.volumesPanel
import com.omasyo.comicsnac.details.components.panels.webViewPanel
import com.omasyo.comicsnac.details.components.shareUrl
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.character.CharacterDetails
import com.omasyo.comicsnac.model.movie.Movie
import com.omasyo.comicsnac.model.team.Team
import com.omasyo.comicsnac.model.volume.Volume
import com.omasyo.comicsnac.ui.components.lazylist.animateScrollAndAlignItem
import com.omasyo.comicsnac.ui.components.webview.rememberComicWebViewContent
import kotlinx.coroutines.launch
import com.omasyo.comicsnac.ui.R.string as CommonString

@Composable
internal fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    onItemClicked: (fullId: String) -> Unit,
    onBackPressed: () -> Unit,
    detailsUiState: DetailsUiState<CharacterDetails>,
    enemies: LazyPagingItems<Character>,
    friends: LazyPagingItems<Character>,
    movies: LazyPagingItems<Movie>,
    teams: LazyPagingItems<Team>,
    teamEnemies: LazyPagingItems<Team>,
    teamFriends: LazyPagingItems<Team>,
    volumes: LazyPagingItems<Volume>
) {
    when (detailsUiState) {
        is Error -> {
            DetailsErrorPlaceholder(onBackPressed = onBackPressed, onRetry = detailsUiState.refresh)
        }

        Loading -> {
            DetailsLoadingPlaceholder(onBackPressed = onBackPressed)
        }

        is Success -> {
            val scope = rememberCoroutineScope()

            var expandedIndex by rememberSaveable { mutableIntStateOf(-1) }

            fun expandedProviderCallback(index: Int) = index == expandedIndex

            with(detailsUiState.content) {

                val webViewContent =
                    rememberComicWebViewContent(description, Domain, onItemClicked)
                val context = LocalContext.current

                DetailsScreen(
                    modifier = modifier,
                    images = listOf(
                        Image(
                            imageUrl, stringResource(CommonString.character_image_desc)
                        ),
                    ),
                    userScrollEnabled = expandedIndex < 0,
                    onBackPressed = onBackPressed,
                    onShareClick = { shareUrl(context, siteDetailUrl) }
                ) { lazyListState ->

                    fun onExpand(index: Int) {
                        scope.launch {
                            if (expandedIndex == index) {
                                expandedIndex = -1
                                lazyListState.animateScrollAndAlignItem(
                                    index, 0.33f
                                )
                            } else {
                                expandedIndex = index
                                lazyListState.animateScrollAndAlignItem(
                                    index
                                )
                            }
                        }
                    }

                    panel {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16f.dp, vertical = 8f.dp),
                            verticalArrangement = Arrangement.spacedBy(4f.dp),
                        ) {
                            Text(name, style = MaterialTheme.typography.headlineMedium)
                            Text(deck, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                    panel {
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16f.dp, vertical = 4f.dp),
                            verticalArrangement = Arrangement.spacedBy(4f.dp)
                        ) {
                            if (realName.isNotBlank()) {
                                Info(name = stringResource(R.string.real_name), content = realName)
                            }
                            origin?.let {
                                Info(name = stringResource(R.string.origin), content = it.name) {
                                    onItemClicked(it.apiDetailUrl)
                                }
                            }
                            Info(name = stringResource(CommonString.gender), content = gender.name)
                            if (aliases.isNotEmpty()) {
                                Info(
                                    name = stringResource(CommonString.aliases),
                                    content = aliases.joinToString(", ")
                                )
                            }
                            Info(
                                name = stringResource(R.string.first_appeared_in_issue),
                                content = firstAppearance.name.ifBlank { "Unknown name" }
                            ) {
                                onItemClicked(firstAppearance.apiDetailUrl)
                            }
                            Info(
                                name = stringResource(R.string.issue_appearances),
                                content = countOfIssueAppearances.toString()
                            )
                            publisher?.let {
                                Info(name = stringResource(R.string.publisher), content = it.name) {
                                    onItemClicked(it.apiDetailUrl)
                                }
                            }
                        }
                    }

                    if (powers.isNotEmpty()) {
                        panelSeparator()

                        panel {
                            DetailsFlow(
                                name = stringResource(CommonString.powers), items = powers
                            ) { power ->
                                Text(
                                    power.name,
                                    Modifier
                                        .clickable { onItemClicked(power.apiDetailUrl) }
                                        .padding(horizontal = 8f.dp),
                                    style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }


                    if (friendsId.isNotEmpty()) {
                        panelSeparator()

                        friendsPanel(
                            friends,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (enemiesId.isNotEmpty()) {
                        panelSeparator()

                        enemiesPanel(
                            enemies,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (teamsId.isNotEmpty()) {
                        panelSeparator()

                        teamsPanel(
                            teams,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (teamFriendsId.isNotEmpty()) {
                        panelSeparator()

                        teamFriendsPanel(
                            teamFriends,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (teamEnemiesId.isNotEmpty()) {
                        panelSeparator()

                        teamEnemiesPanel(
                            teamEnemies,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (description.isNotBlank()) {
                        webViewPanel(
                            webViewContent,
                            ::expandedProviderCallback,
                            ::onExpand,
                        )
                    } else if (volumeCreditsId.isNotEmpty()) {
                        panelSeparator()
                    }

                    if (volumeCreditsId.isNotEmpty()) {
                        volumesPanel(
                            volumes,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (moviesId.isNotEmpty()) {
                        panelSeparator()

                        moviesPanel(
                            movies,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (creators.isNotEmpty()) {
                        panelSeparator()

                        panel {
                            DetailsFlow(
                                name = stringResource(CommonString.creators), items = creators
                            ) { person ->
                                Text(
                                    person.name,
                                    Modifier
                                        .clickable { onItemClicked(person.apiDetailUrl) }
                                        .padding(horizontal = 16f.dp),
                                    style = MaterialTheme.typography.titleLarge)
                            }
                        }
                    }
                }
            }
        }
    }
}