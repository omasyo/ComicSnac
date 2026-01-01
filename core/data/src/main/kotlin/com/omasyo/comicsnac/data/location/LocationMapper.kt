package com.omasyo.comicsnac.data.location

import com.omasyo.comicsnac.data.issue.toIssueBasic
import com.omasyo.comicsnac.model.location.Location
import com.omasyo.comicsnac.model.location.LocationDetails
import com.omasyo.comicsnac.network.location.models.LocationDetailsApiModel
import com.omasyo.comicsnac.network.search.models.LocationListApiModel

internal fun List<LocationListApiModel>.toLocations() = map { apiModel -> apiModel.toLocation() }

internal fun LocationListApiModel.toLocation() = Location(
    apiDetailUrl = apiDetailUrl,
    deck = deck ?: "",
    id = id,
    imageUrl = image.smallUrl,
    name = name
)

internal fun LocationDetailsApiModel.toLocationDetails() =
    LocationDetails(
        apiDetailUrl = apiDetailUrl,
        countOfIssueAppearances = countOfIssueAppearances,
        deck = deck ?: "",
        description = description ?: "",
        firstAppearedInIssue = firstAppearedInIssue.toIssueBasic(),
        id = id,
        imageUrl = image.smallUrl,
        name = name,
        siteDetailUrl = siteDetailUrl
    )