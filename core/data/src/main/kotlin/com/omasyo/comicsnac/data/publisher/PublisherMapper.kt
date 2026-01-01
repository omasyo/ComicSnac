package com.omasyo.comicsnac.data.publisher

import com.omasyo.comicsnac.model.publisher.Publisher
import com.omasyo.comicsnac.model.publisher.PublisherBasic
import com.omasyo.comicsnac.model.publisher.PublisherDetails
import com.omasyo.comicsnac.network.common.models.PublisherApiModel
import com.omasyo.comicsnac.network.common.models.StudioApiModel
import com.omasyo.comicsnac.network.publisher.models.PublisherDetailsApiModel
import com.omasyo.comicsnac.network.search.models.PublisherListApiModel

internal fun PublisherApiModel.toPublisherBasic() = PublisherBasic(
    apiDetailUrl = apiDetailUrl, id = id, name = name
)

internal fun PublisherListApiModel.toPublisher() = Publisher(
    apiDetailUrl = apiDetailUrl,
    deck = deck ?: "",
    id = id,
    imageUrl = image.smallUrl,
    name = name
)

internal fun StudioApiModel.toPublisher() =
    PublisherBasic(apiDetailUrl = apiDetailUrl, id = id, name = name)

internal fun List<PublisherListApiModel>.toPublishers() = map { apiModel -> apiModel.toPublisher() }

internal fun PublisherDetailsApiModel.toPublisherDetails() =
    PublisherDetails(
        aliases = aliases?.split("\n") ?: emptyList(),
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        description = description ?: "",
        id = id,
        imageUrl = image.smallUrl,
        location = buildList {
            locationAddress?.let(::add)
            locationCity?.let(::add)
            locationState?.let(::add)
        }.joinToString(" "),
        name = name,
        siteDetailUrl = siteDetailUrl,
        teamsId = teams.map { it.id }

    )