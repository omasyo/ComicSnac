package com.omasyo.comicsnac.details.storyarc

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
import com.omasyo.comicsnac.details.components.DetailsLoadingPlaceholder
import com.omasyo.comicsnac.details.components.DetailsScreen
import com.omasyo.comicsnac.details.components.Image
import com.omasyo.comicsnac.details.components.Info
import com.omasyo.comicsnac.details.components.panels.episodesPanel
import com.omasyo.comicsnac.details.components.panels.issuesPanel
import com.omasyo.comicsnac.details.components.panels.webViewPanel
import com.omasyo.comicsnac.details.components.shareUrl
import com.omasyo.comicsnac.model.episode.Episode
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.model.storyarc.StoryArcDetails
import com.omasyo.comicsnac.ui.components.lazylist.animateScrollAndAlignItem
import com.omasyo.comicsnac.ui.components.webview.rememberComicWebViewContent
import kotlinx.coroutines.launch
import com.omasyo.comicsnac.ui.R.string as CommonString

@Composable
internal fun StoryArcDetailsScreen(
    modifier: Modifier = Modifier,
    onItemClicked: (fullId: String) -> Unit,
    onBackPressed: () -> Unit,
    detailsUiState: DetailsUiState<StoryArcDetails>,
    episodes: LazyPagingItems<Episode>,
    issues: LazyPagingItems<Issue>
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
                        Image(imageUrl, stringResource(CommonString.issue_image_desc))
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
                            firstAppearedInIssue?.let {
                                Info(
                                    name = stringResource(R.string.first_appeared_in_issue),
                                    content = it.name
                                ) { onItemClicked(it.apiDetailUrl) }
                            }
                            firstAppearedInEpisode?.let {
                                Info(
                                    name = stringResource(R.string.first_appeared_in_episode),
                                    content = it.name
                                ) { onItemClicked(it.apiDetailUrl) }
                            }
                            publisher?.let {
                                Info(
                                    name = stringResource(R.string.publisher),
                                    content = it.name
                                ) { onItemClicked(it.apiDetailUrl) }
                            }
                        }
                    }

                    if (description.isNotBlank()) {
                        webViewPanel(
                            webViewContent,
                            ::expandedProviderCallback,
                            ::onExpand,
                        )
                    } else if (issuesId.isNotEmpty()) {
                        panelSeparator()
                    }

                    if (issuesId.isNotEmpty()) {
                        issuesPanel(
                            issues,
                            ::expandedProviderCallback,
                            ::onExpand,
                            onItemClicked
                        )
                    }

                    if (episodesId.isNotEmpty()) {
                        if (issuesId.isNotEmpty()) {
                            panelSeparator()
                        }

                        episodesPanel(
                            episodes,
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