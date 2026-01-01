package com.omasyo.comicsnac.categories.publisher

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.publisher.PublisherRepository
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.model.publisher.Publisher
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class PublisherViewModel @Inject constructor(
    publisherRepository: PublisherRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Publisher>(settingsRepository) {

    override val items =
        publisherRepository.getAllPublishers().cachedIn(viewModelScope)

}

