package com.omasyo.comicsnac.model.volume

import com.omasyo.comicsnac.model.search.SearchModel

data class Volume(
    override val apiDetailUrl: String,
    override val deck: String,
    override val id: Int,
    override val imageUrl: String,
    override val name: String
) : SearchModel
