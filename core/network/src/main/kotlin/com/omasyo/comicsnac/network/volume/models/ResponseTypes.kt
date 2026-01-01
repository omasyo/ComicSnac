package com.omasyo.comicsnac.network.volume.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.VolumeListApiModel

typealias VolumeDetailsResponse = ResponseApiModel<VolumeDetailsApiModel>

typealias VolumeListResponse = ResponseApiModel<List<VolumeListApiModel>>