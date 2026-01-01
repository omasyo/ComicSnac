package com.omasyo.comicsnac.categories.storyarcs

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.data.storyarc.StoryArcRepository
import com.omasyo.comicsnac.model.storyarc.StoryArc
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class StoryArcViewModel @Inject constructor(
    storyArcRepository: StoryArcRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<StoryArc>(settingsRepository) {

    override val items =
        storyArcRepository.getAllStoryArcs().cachedIn(viewModelScope)

}

