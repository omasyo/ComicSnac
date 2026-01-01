package com.omasyo.comicsnac.categories.origin

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.origin.OriginRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.origin.OriginBasic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class OriginViewModel @Inject constructor(
    originRepository: OriginRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<OriginBasic>(settingsRepository) {

    override val items =
        originRepository.getAllOrigins().cachedIn(viewModelScope)

}

