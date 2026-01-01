package com.omasyo.comicsnac.network.storyarc.models


import com.omasyo.comicsnac.network.common.models.EpisodeApiModel
import com.omasyo.comicsnac.network.common.models.ImageApiModel
import com.omasyo.comicsnac.network.common.models.IssueApiModel
import com.omasyo.comicsnac.network.common.models.PublisherApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StoryArcDetailsApiModel(
    @SerialName("api_detail_url") val apiDetailUrl: String,
    @SerialName("deck") val deck: String?,
    @SerialName("description") val description: String?,
    @SerialName("episodes") val episodes: List<EpisodeApiModel> = emptyList(),
    @SerialName("first_appeared_in_episode") val firstAppearedInEpisode: EpisodeApiModel?,
    @SerialName("first_appeared_in_issue") val firstAppearedInIssue: IssueApiModel,
    @SerialName("id") val id: Int,
    @SerialName("image") val image: ImageApiModel,
    @SerialName("issues") val issues: List<IssueApiModel>,
    @SerialName("name") val name: String,
    @SerialName("publisher") val publisher: PublisherApiModel?,
    @SerialName("site_detail_url") val siteDetailUrl: String
)