package com.omasyo.comicsnac.details.issue

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
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
import com.omasyo.comicsnac.details.components.panels.charactersPanel
import com.omasyo.comicsnac.details.components.panels.locationsPanel
import com.omasyo.comicsnac.details.components.panels.objectsPanel
import com.omasyo.comicsnac.details.components.panels.storyArcsPanel
import com.omasyo.comicsnac.details.components.panels.teamsPanel
import com.omasyo.comicsnac.details.components.panels.webViewPanel
import com.omasyo.comicsnac.details.components.shareUrl
import com.omasyo.comicsnac.details.formatDate
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.issue.IssueDetails
import com.omasyo.comicsnac.model.location.Location
import com.omasyo.comicsnac.model.`object`.ObjectItem
import com.omasyo.comicsnac.model.storyarc.StoryArc
import com.omasyo.comicsnac.model.team.Team
import com.omasyo.comicsnac.ui.components.lazylist.animateScrollAndAlignItem
import com.omasyo.comicsnac.ui.components.webview.rememberComicWebViewContent
import kotlinx.coroutines.launch
import com.omasyo.comicsnac.ui.R.string as CommonString

@Composable
internal fun IssueDetailsScreen(
    modifier: Modifier = Modifier,
    onItemClicked: (fullId: String) -> Unit,
    onBackPressed: () -> Unit,
    detailsUiState: DetailsUiState<IssueDetails>,
    characters: LazyPagingItems<Character>,
    locations: LazyPagingItems<Location>,
    objects: LazyPagingItems<ObjectItem>,
    storyArcs: LazyPagingItems<StoryArc>,
    teams: LazyPagingItems<Team>,
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
                    images = (listOf(imageUrl) + associatedImagesUrl).map {
                        Image(
                            it,
                            stringResource(
                                CommonString.issue_image_desc,
                                issueNumber,
                                volume.name,
                                name
                            )
                        )
                    },
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
                            Text(
                                "${volume.name} #$issueNumber",
                                style = MaterialTheme.typography.headlineMedium,
                                textDecoration = TextDecoration.Underline,
                                modifier = Modifier.clickable { onItemClicked(volume.apiDetailUrl) }
                            )
                            Text(name, style = MaterialTheme.typography.headlineSmall)
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
                            coverDate?.let {
                                Info(
                                    name = stringResource(R.string.cover_date),
                                    content = it.formatDate()
                                )
                            }
                            storeDate?.let {
                                Info(
                                    name = stringResource(R.string.store_date),
                                    content = it.formatDate()
                                )
                            }
                        }
                    }

                    if (credits.isNotEmpty()) {
                        panelSeparator()

                        panel {
                            DetailsFlow(
                                name = stringResource(R.string.credits), items = credits
                            ) { credit ->
                                Column(
                                    Modifier.padding(horizontal = 16f.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        credit.name,
                                        Modifier
                                            .clickable { onItemClicked(credit.apiDetailUrl) }
                                            .padding(horizontal = 16f.dp),
                                        style = MaterialTheme.typography.titleLarge)
                                    Text(
                                        credit.role,
                                        Modifier
                                            .padding(horizontal = 8f.dp),
                                        style = MaterialTheme.typography.titleMedium
                                    )
                                }
                            }
                        }
                    }

                    if (charactersId.isNotEmpty()) {
                        panelSeparator()

                        charactersPanel(
                            CommonString.characters,
                            characters,
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

                    if (description.isNotBlank()) {
                        webViewPanel(
                            webViewContent,
                            ::expandedProviderCallback,
                            ::onExpand,
                        )
                    }

                    if (concepts.isNotEmpty()) {
                        if (description.isBlank()) {
                            panelSeparator()
                        }

                        panel {
                            DetailsFlow(
                                name = stringResource(CommonString.concepts), items = concepts
                            ) { concept ->
                                Text(
                                    concept.name,
                                    Modifier
                                        .clickable { onItemClicked(concept.apiDetailUrl) }
                                        .padding(horizontal = 8f.dp),
                                    style = MaterialTheme.typography.titleMedium)
                            }
                        }
                    }

                    if (locationsId.isNotEmpty()) {
                        if (description.isBlank() || concepts.isNotEmpty()) {
                            panelSeparator()
                        }

                        locationsPanel(
                            locations,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (objectsId.isNotEmpty()) {
                        if (description.isBlank() || concepts.isNotEmpty() || locationsId.isNotEmpty()) {
                            panelSeparator()
                        }

                        objectsPanel(
                            objects,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (storyArcsId.isNotEmpty()) {
                        if (description.isBlank() || concepts.isNotEmpty() || locationsId.isNotEmpty() || objectsId.isNotEmpty()) {
                            panelSeparator()
                        }

                        storyArcsPanel(
                            storyArcs,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }
                }
            }
        }
    }
}