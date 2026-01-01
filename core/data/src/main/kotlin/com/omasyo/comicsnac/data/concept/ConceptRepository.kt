package com.omasyo.comicsnac.data.concept

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.concept.Concept
import com.omasyo.comicsnac.model.concept.ConceptDetails
import kotlinx.coroutines.flow.Flow

interface ConceptRepository {
    fun getConceptDetails(id: String): Flow<RepositoryResponse<ConceptDetails>>

    fun getAllConcepts(): Flow<PagingData<Concept>>
}