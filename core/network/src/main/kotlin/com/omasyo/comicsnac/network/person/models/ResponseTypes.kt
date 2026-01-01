package com.omasyo.comicsnac.network.person.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.PersonListApiModel

typealias PersonDetailsResponse = ResponseApiModel<PersonDetailsApiModel>

typealias PersonListResponse = ResponseApiModel<List<PersonListApiModel>>