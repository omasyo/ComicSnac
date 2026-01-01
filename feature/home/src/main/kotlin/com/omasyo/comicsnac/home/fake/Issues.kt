package com.omasyo.comicsnac.home.fake

import com.omasyo.comicsnac.model.issue.Issue

val Issues = List(30) {
    Issue(
        apiDetailUrl = "https://search.yahoo.com/search?p=qui",
        deck = "vix",
        id = it,
        imageUrl = "https://comicvine.gamespot.com/a/uploads/scale_small/6/67663/2710974-698.jpg",
        name = "Angelo Espinoza $it",
        issueNumber = "23",
        volumeName = "Anderson Sawyer"
    )
}