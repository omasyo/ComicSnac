package com.omasyo.comicsnac.network.concept.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.ConceptListApiModel

typealias ConceptDetailsResponse = ResponseApiModel<ConceptDetailsApiModel>

typealias ConceptListResponse = ResponseApiModel<List<ConceptListApiModel>>