package com.omasyo.comicsnac.categories.locations

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.location.LocationRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.location.Location
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LocationViewModel @Inject constructor(
    locationRepository: LocationRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Location>(settingsRepository) {

    override val items =
        locationRepository.getAllLocations().cachedIn(viewModelScope)

}

