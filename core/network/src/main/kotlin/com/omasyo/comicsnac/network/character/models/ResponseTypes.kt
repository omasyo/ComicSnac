package com.omasyo.comicsnac.network.character.models

import com.omasyo.comicsnac.network.common.models.ResponseApiModel
import com.omasyo.comicsnac.network.search.models.CharacterListApiModel

typealias CharacterDetailsResponse = ResponseApiModel<CharacterDetailsApiModel>

typealias CharactersListResponse = ResponseApiModel<List<CharacterListApiModel>>