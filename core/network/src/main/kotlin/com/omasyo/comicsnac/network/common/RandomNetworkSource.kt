package com.omasyo.comicsnac.network.common

import com.omasyo.comicsnac.network.NetworkSource
import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.makeRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

interface RandomNetworkSource : NetworkSource {
    suspend fun verifyApiKey(apiKey: String): Result<ResponseApiModel<List<Nothing>>>
}

internal class DefaultRandomNetworkSource @Inject constructor(
    private val client: HttpClient
) : RandomNetworkSource {
    override suspend fun verifyApiKey(apiKey: String): Result<ResponseApiModel<List<Nothing>>> =
        makeRequest {
            client.get("chats") {
                parameter("api_key", apiKey)
            }
        }

}