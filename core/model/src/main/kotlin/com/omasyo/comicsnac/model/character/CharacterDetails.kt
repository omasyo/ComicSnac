package com.omasyo.comicsnac.model.character

import com.omasyo.comicsnac.model.issue.IssueBasic
import com.omasyo.comicsnac.model.origin.OriginBasic
import com.omasyo.comicsnac.model.other.Gender
import com.omasyo.comicsnac.model.person.PersonBasic
import com.omasyo.comicsnac.model.power.PowerBasic
import com.omasyo.comicsnac.model.publisher.PublisherBasic

data class CharacterDetails(
    val id: Int,
    val aliases: List<String>,
    val apiDetailUrl: String,
    val countOfIssueAppearances: Int,
    val creators: List<PersonBasic>,
    val deck: String,
    val description: String,
    val firstAppearance: IssueBasic,
    val enemiesId: List<Int>,
    val friendsId: List<Int>,
    val gender: Gender,
    val imageUrl: String,
    val moviesId: List<Int>,
    val name: String,
    val origin: OriginBasic?,
    val powers: List<PowerBasic>,
    val publisher: PublisherBasic?,
    val realName: String,
    val siteDetailUrl: String,
    val teamEnemiesId: List<Int>,
    val teamFriendsId: List<Int>,
    val teamsId: List<Int>,
    val volumeCreditsId: List<Int>
)
