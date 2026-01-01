package com.omasyo.comicsnac.network.origin.models

import com.omasyo.comicsnac.network.common.models.OriginApiModel
import com.omasyo.comicsnac.network.common.models.ResponseApiModel

typealias OriginDetailsResponse = ResponseApiModel<OriginDetailsApiModel>

typealias OriginListResponse = ResponseApiModel<List<OriginApiModel>>