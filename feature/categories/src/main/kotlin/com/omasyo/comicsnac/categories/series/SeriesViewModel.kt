package com.omasyo.comicsnac.categories.series

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.series.SeriesRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.series.Series
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class SeriesViewModel @Inject constructor(
    seriesRepository: SeriesRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Series>(settingsRepository) {

    override val items =
        seriesRepository.getAllSeries().cachedIn(viewModelScope)

}

