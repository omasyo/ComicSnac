package com.omasyo.comicsnac.categories.character

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.character.CharacterRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.character.Character
import com.omasyo.comicsnac.model.other.Gender
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class CharacterViewModel @Inject constructor(
    characterRepository: CharacterRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Character>(settingsRepository) {

    override val items =
        characterRepository.getAllCharacters(Gender.All).cachedIn(viewModelScope)

}

