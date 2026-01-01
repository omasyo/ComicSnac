package com.omasyo.comicsnac.categories.concept

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.concept.ConceptRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.concept.Concept
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConceptViewModel @Inject constructor(
    conceptRepository: ConceptRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Concept>(settingsRepository) {

    override val items =
        conceptRepository.getAllConcepts().cachedIn(viewModelScope)

}

