package com.omasyo.comicsnac.categories.power

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.power.PowerRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.power.PowerBasic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class PowerViewModel @Inject constructor(
    powerRepository: PowerRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<PowerBasic>(settingsRepository) {

    override val items =
        powerRepository.getAllPowers().cachedIn(viewModelScope)

}

