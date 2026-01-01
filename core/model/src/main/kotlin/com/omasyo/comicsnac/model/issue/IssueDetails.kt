package com.omasyo.comicsnac.model.issue

import com.omasyo.comicsnac.model.concept.ConceptBasic
import com.omasyo.comicsnac.model.person.PersonCredit
import com.omasyo.comicsnac.model.volume.VolumeBasic
import java.time.LocalDate

data class IssueDetails(
    val apiDetailUrl: String,
    val associatedImagesUrl: List<String>,
    val credits: List<PersonCredit>,
    val charactersId: List<Int>,
    val concepts: List<ConceptBasic>,
    val coverDate: LocalDate?,
    val deck: String,
    val description: String,
    val id: Int,
    val imageUrl: String,
    val issueNumber: String,
    val locationsId: List<Int>,
    val name: String,
    val objectsId: List<Int>,
    val siteDetailUrl: String,
    val storeDate: LocalDate?,
    val storyArcsId: List<Int>,
    val teamsId: List<Int>,
    val volume: VolumeBasic
)
