package com.omasyo.comicsnac.data.search

import androidx.paging.PagingData
import com.omasyo.comicsnac.model.search.SearchModel
import com.omasyo.comicsnac.model.search.SearchType
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getSearchResults(query: String, filter: Set<SearchType>): Flow<PagingData<SearchModel>>
}