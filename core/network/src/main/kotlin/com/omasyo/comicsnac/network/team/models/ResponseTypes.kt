package com.omasyo.comicsnac.network.team.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.TeamListApiModel

typealias TeamDetailsResponse = ResponseApiModel<TeamDetailsApiModel>

typealias TeamListResponse = ResponseApiModel<List<TeamListApiModel>>