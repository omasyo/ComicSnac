package com.omasyo.comicsnac.data.volume

import com.omasyo.comicsnac.data.issue.toIssueBasic
import com.omasyo.comicsnac.data.publisher.toPublisherBasic
import com.omasyo.comicsnac.model.volume.Volume
import com.omasyo.comicsnac.model.volume.VolumeBasic
import com.omasyo.comicsnac.model.volume.VolumeDetails
import com.omasyo.comicsnac.network.common.models.VolumeApiModel
import com.omasyo.comicsnac.network.volume.models.VolumeDetailsApiModel
import com.omasyo.comicsnac.network.search.models.VolumeListApiModel

internal fun VolumeApiModel.toBasic() = VolumeBasic(
    apiDetailUrl = apiDetailUrl,
    id = id,
    name = name
)

internal fun List<VolumeListApiModel>.toVolumes() = map { apiModel -> apiModel.toVolume() }

internal fun VolumeListApiModel.toVolume() = Volume(
    apiDetailUrl = apiDetailUrl,
    deck = deck ?: "",
    id = id,
    imageUrl = image.smallUrl,
    name = name
)

internal fun VolumeDetailsApiModel.toVolumeDetails() =
    VolumeDetails(
        apiDetailUrl = apiDetailUrl,
        countOfIssues = countOfIssues,
        deck = deck ?: "",
        description = description ?: "",
        firstIssue = firstIssue?.toIssueBasic(),
        id = id,
        imageUrl = image.smallUrl,
        issuesId = issues.map { it.id },
        lastIssue = lastIssue?.toIssueBasic(),
        name = name,
        publisher = publisher?.toPublisherBasic(),
        siteDetailUrl = siteDetailUrl,
        startYear = startYear ?: ""
    )