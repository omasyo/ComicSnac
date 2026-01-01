package com.omasyo.comicsnac.network.person

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.person.models.PersonDetailsResponse
import com.omasyo.comicsnac.network.person.models.PersonListResponse

interface PersonNetworkSource : NetworkSource {
    suspend fun getPersonDetails(apiKey: String, id: String): Result<PersonDetailsResponse>

    suspend fun getAllPeople(
        apiKey: String,
        pageSize: Int,
        offset: Int,
    ): Result<PersonListResponse>
}