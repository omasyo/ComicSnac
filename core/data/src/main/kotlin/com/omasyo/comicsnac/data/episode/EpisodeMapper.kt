package com.omasyo.comicsnac.data.episode

import com.omasyo.comicsnac.data.series.toSeriesBasic
import com.omasyo.comicsnac.model.episode.Episode
import com.omasyo.comicsnac.model.episode.EpisodeBasic
import com.omasyo.comicsnac.model.episode.EpisodeDetails
import com.omasyo.comicsnac.network.common.models.EpisodeApiModel
import com.omasyo.comicsnac.network.episode.models.EpisodeDetailsApiModel
import com.omasyo.comicsnac.network.episode.models.EpisodeListApiModel

fun EpisodeApiModel.toEpisodeBasic() =
    EpisodeBasic(apiDetailUrl = apiDetailUrl, id = id, name = name)

fun List<EpisodeListApiModel>.toEpisodes() = map { apiModel -> apiModel.toEpisode() }

fun EpisodeListApiModel.toEpisode() =
    Episode(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name,
        seriesName = series.name
    )

fun EpisodeDetailsApiModel.toEpisodeDetails() =
    EpisodeDetails(
        airDate = airDate,
        apiDetailUrl = apiDetailUrl,
        charactersId = characterCredits.map { it.id },
        deck = deck ?: "",
        description = description ?: "",
        episodeNumber = episodeNumber,
        id = id,
        imageUrl = image.smallUrl,
        locationsId = locationCredits.map { it.id },
        name = name,
        objectsId = objectCredits.map { it.id },
        series = series.toSeriesBasic(),
        siteDetailUrl = siteDetailUrl,
        teamsId = teamCredits.map { it.id }
    )