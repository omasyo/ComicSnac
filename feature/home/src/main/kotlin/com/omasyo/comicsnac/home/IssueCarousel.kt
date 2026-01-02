package com.omasyo.comicsnac.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.ColorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.omasyo.comicsnac.home.fake.Issues
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.ui.R
import com.omasyo.comicsnac.ui.theme.ComicSnacTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun IssueCarousel(
    issues: List<Issue>,
    onIssueClick: (apiUrl: String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Carousel(
        items = issues,
        onItemClick = { onIssueClick(it.apiDetailUrl) },
        modifier = modifier,
    ) {
        IssueCard(issue = it, interactionSource = interactionSource)
    }
}

@Composable
private fun IssueCard(
    issue: Issue,
    modifier: Modifier = Modifier,
    interactionSource: InteractionSource? = null,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {

        Box(
            Modifier
                .then(
                    if (interactionSource != null) Modifier.indication(
                        interactionSource,
                        ripple()
                    ) else Modifier
                )
                .fillMaxWidth(1f)
                .aspectRatio(11f / 17f)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current).data(issue.imageUrl)
                    .crossfade(true).build(),

                contentDescription = stringResource(
                    R.string.issue_image_desc,
                    issue.issueNumber,
                    issue.volumeName,
                    issue.name,
                ),
                placeholder = ColorPainter(Color.Gray),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .shadow(6f.dp)
            )
        }

        Spacer(Modifier.height(8f.dp))
        Text(
            text = issue.name,
            textAlign = TextAlign.Center,
            minLines = 2,
            maxLines = 2,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun IssuePreview() {
    ComicSnacTheme {
        IssueCard(issue = Issues[0])
    }
}

@Preview
@Preview(device = "spec:parent=pixel_5,orientation=landscape")
@Composable
private fun Preview() {
    ComicSnacTheme {
        Surface(Modifier.fillMaxSize()) {
            IssueCarousel(issues = Issues, onIssueClick = {})
        }
    }
}
