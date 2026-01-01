package com.omasyo.comicsnac.categories.volume

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.data.volume.VolumeRepository
import com.omasyo.comicsnac.model.volume.Volume
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class VolumeViewModel @Inject constructor(
    volumeRepository: VolumeRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Volume>(settingsRepository) {

    override val items =
        volumeRepository.getAllVolumes().cachedIn(viewModelScope)

}

