package com.omasyo.comicsnac.details.location

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.omasyo.comicsnac.data.location.LocationRepository
import com.omasyo.comicsnac.details.Arg
import com.omasyo.comicsnac.details.RefreshWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class LocationViewModel @Inject constructor(
    locationRepository: LocationRepository,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val id = checkNotNull(savedStateHandle.get<String>(Arg))

    val detailsUiState =
        RefreshWrapper(viewModelScope) { locationRepository.getLocationDetails(id) }.response
}

