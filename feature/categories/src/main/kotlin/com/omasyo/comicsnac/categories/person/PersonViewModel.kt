package com.omasyo.comicsnac.categories.person

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.person.PersonRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.person.Person
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class PersonViewModel @Inject constructor(
    personRepository: PersonRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Person>(settingsRepository) {

    override val items =
        personRepository.getAllPeople().cachedIn(viewModelScope)

}

