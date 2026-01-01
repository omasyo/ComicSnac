package com.omasyo.comicsnac.network.`object`.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.ObjectListApiModel

typealias ObjectDetailsResponse = ResponseApiModel<ObjectDetailsApiModel>

typealias ObjectListResponse = ResponseApiModel<List<ObjectListApiModel>>