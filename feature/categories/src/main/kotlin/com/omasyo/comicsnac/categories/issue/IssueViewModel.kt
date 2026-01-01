package com.omasyo.comicsnac.categories.issue

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.data.issue.IssueRepository
import com.omasyo.comicsnac.model.issue.Issue
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class IssueViewModel @Inject constructor(
    issuesRepository: IssueRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Issue>(settingsRepository) {

    override val items =
        issuesRepository.getAllIssues().cachedIn(viewModelScope)

}

