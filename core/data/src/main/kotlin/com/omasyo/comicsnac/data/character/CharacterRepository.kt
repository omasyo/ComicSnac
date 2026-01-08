package com.omasyo.comicsnac.data.character

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.character.CharacterDetails
import com.omasyo.comicsnac.model.other.Gender
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacterDetails(id: String): Flow<RepositoryResponse<CharacterDetails>>

    fun getRecentCharacters(): Flow<RepositoryResponse<List<Character>>>

    fun getAllCharacters(): Flow<PagingData<Character>>

    fun getCharactersWithId(charactersId: List<Int>): Flow<PagingData<Character>>
}