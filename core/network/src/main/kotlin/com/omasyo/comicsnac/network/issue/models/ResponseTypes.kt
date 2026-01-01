package com.omasyo.comicsnac.network.issue.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.IssueListApiModel

typealias IssueDetailsResponse = ResponseApiModel<IssueDetailsApiModel>

typealias IssueListResponse = ResponseApiModel<List<IssueListApiModel>>