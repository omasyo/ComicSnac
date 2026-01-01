package com.omasyo.comicsnac.network.storyarc.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.StoryArcListApiModel

typealias StoryArcDetailsResponse = ResponseApiModel<StoryArcDetailsApiModel>

typealias StoryArcListResponse = ResponseApiModel<List<StoryArcListApiModel>>