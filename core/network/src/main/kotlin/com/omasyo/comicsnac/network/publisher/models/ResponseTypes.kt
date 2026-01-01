package com.omasyo.comicsnac.network.publisher.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.PublisherListApiModel

typealias PublisherDetailsResponse = ResponseApiModel<PublisherDetailsApiModel>

typealias PublisherCharactersResponse = ResponseApiModel<PublisherCharactersApiModel>

typealias PublisherVolumesResponse = ResponseApiModel<PublisherVolumesApiModel>

typealias PublisherListResponse = ResponseApiModel<List<PublisherListApiModel>>