package com.omasyo.comicsnac.network.publisher.models


import com.omasyo.comicsnac.network.common.models.CharacterApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublisherCharactersApiModel(
    @SerialName("characters")
    val characters: List<CharacterApiModel>
)