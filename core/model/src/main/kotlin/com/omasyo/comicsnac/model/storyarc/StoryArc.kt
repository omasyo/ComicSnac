package com.omasyo.comicsnac.model.storyarc

import com.omasyo.comicsnac.model.search.SearchModel

data class StoryArc(
    override val apiDetailUrl: String,
    override val deck: String,
    override val id: Int,
    override val imageUrl: String,
    override val name: String
) : SearchModel
