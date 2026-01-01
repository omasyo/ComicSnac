package com.omasyo.comicsnac.network.concept

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.concept.models.ConceptDetailsResponse
import com.omasyo.comicsnac.network.concept.models.ConceptListResponse

interface ConceptNetworkSource : NetworkSource {
    suspend fun getConceptDetails(apiKey: String, id: String): Result<ConceptDetailsResponse>

    suspend fun getAllConcepts(
        apiKey: String,
        pageSize: Int,
        offset: Int
    ): Result<ConceptListResponse>
}