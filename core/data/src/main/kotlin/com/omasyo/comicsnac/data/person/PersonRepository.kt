package com.omasyo.comicsnac.data.person

import androidx.paging.PagingData
import com.omasyo.comicsnac.data.RepositoryResponse
import com.omasyo.comicsnac.model.Sort
import com.omasyo.comicsnac.model.person.Person
import com.omasyo.comicsnac.model.person.PersonDetails
import kotlinx.coroutines.flow.Flow

interface PersonRepository {
    fun getPersonDetails(id: String): Flow<RepositoryResponse<PersonDetails>>

    fun getAllPeople(sort: Sort = Sort.Descending): Flow<PagingData<Person>>
}