package com.omasyo.comicsnac.model.concept

import com.omasyo.comicsnac.model.issue.IssueBasic

data class ConceptDetails(
    val aliases: List<String>,
    val apiDetailUrl: String,
    val deck: String,
    val description: String,
    val firstAppearedInIssue: IssueBasic,
    val id: Int,
    val imageUrl: String,
    val issuesId: List<Int>,
    val name: String,
    val siteDetailUrl: String,
    val volumesId: List<Int>
)
