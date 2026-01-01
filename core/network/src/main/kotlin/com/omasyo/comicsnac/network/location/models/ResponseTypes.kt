package com.omasyo.comicsnac.network.location.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.LocationListApiModel

typealias LocationDetailsResponse = ResponseApiModel<LocationDetailsApiModel>

typealias LocationListResponse = ResponseApiModel<List<LocationListApiModel>>