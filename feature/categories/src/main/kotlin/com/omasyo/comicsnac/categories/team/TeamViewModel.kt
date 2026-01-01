package com.omasyo.comicsnac.categories.team

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.omasyo.comicsnac.categories.CategoryViewModel
import com.omasyo.comicsnac.data.settings.SettingsRepository
import com.omasyo.comicsnac.data.team.TeamRepository
import com.omasyo.comicsnac.model.team.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class TeamViewModel @Inject constructor(
    teamRepository: TeamRepository,
    settingsRepository: SettingsRepository
) : CategoryViewModel<Team>(settingsRepository) {

    override val items =
        teamRepository.getAllTeams().cachedIn(viewModelScope)

}

