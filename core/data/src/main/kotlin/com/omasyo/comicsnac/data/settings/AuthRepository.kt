package com.omasyo.comicsnac.data.settings

import com.omasyo.comicsnac.data.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun verifyApiKey(key: String): RepositoryResponse<Unit>

    suspend fun updateApiKey(key: String)

    fun getApiKey(): Flow<String>
}