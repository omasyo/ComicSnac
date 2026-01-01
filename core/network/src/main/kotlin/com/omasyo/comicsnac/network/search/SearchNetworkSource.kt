package com.omasyo.comicsnac.network.search

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.SearchApiModel

interface SearchNetworkSource : NetworkSource {
    suspend fun getSearchResults(
        apiKey: String,
        query: String,
        filter: String,
        pageSize: Int,
        offset: Int
    ): Result<ResponseApiModel<List<SearchApiModel>>>
}