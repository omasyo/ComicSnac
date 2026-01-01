package com.omasyo.comicsnac.data.search

import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.concept.Concept
import com.omasyo.comicsnac.model.issue.Issue
import com.omasyo.comicsnac.model.location.Location
import com.omasyo.comicsnac.model.`object`.ObjectItem
import com.omasyo.comicsnac.model.person.Person
import com.omasyo.comicsnac.model.publisher.Publisher
import com.omasyo.comicsnac.model.storyarc.StoryArc
import com.omasyo.comicsnac.model.team.Team
import com.omasyo.comicsnac.model.volume.Volume
import com.omasyo.comicsnac.network.search.models.CharacterListApiModel
import com.omasyo.comicsnac.network.search.models.ConceptListApiModel
import com.omasyo.comicsnac.network.search.models.IssueListApiModel
import com.omasyo.comicsnac.network.search.models.LocationListApiModel
import com.omasyo.comicsnac.network.search.models.ObjectListApiModel
import com.omasyo.comicsnac.network.search.models.PersonListApiModel
import com.omasyo.comicsnac.network.search.models.PublisherListApiModel
import com.omasyo.comicsnac.network.search.models.SearchApiModel
import com.omasyo.comicsnac.network.search.models.StoryArcListApiModel
import com.omasyo.comicsnac.network.search.models.TeamListApiModel
import com.omasyo.comicsnac.network.search.models.VolumeListApiModel

fun List<SearchApiModel>.toSearchModels() = map { apiModel -> apiModel.toSearchModel() }

fun SearchApiModel.toSearchModel() = when (this) {
    is CharacterListApiModel -> Character(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )

    is ConceptListApiModel -> Concept(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )

    is IssueListApiModel -> Issue(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        issueNumber = issueNumber,
        name = name ?: "",
        volumeName = volume.name
    )

    is LocationListApiModel -> Location(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )

    is ObjectListApiModel -> ObjectItem(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )

    is PersonListApiModel -> Person(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )

    is PublisherListApiModel -> Publisher(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = ""
    )

    is StoryArcListApiModel -> StoryArc(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )

    is TeamListApiModel -> Team(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )

    is VolumeListApiModel -> Volume(
        apiDetailUrl = apiDetailUrl,
        deck = deck ?: "",
        id = id,
        imageUrl = image.smallUrl,
        name = name
    )
}