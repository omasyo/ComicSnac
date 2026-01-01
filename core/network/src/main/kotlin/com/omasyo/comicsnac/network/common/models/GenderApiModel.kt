package com.omasyo.comicsnac.network.common.models

import com.omasyo.comicsnac.network.common.serializers.GenderAsIntSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GenderAsIntSerializer::class)
enum class GenderApiModel(val id: Int) {
    All(-1), Female(2), Male(1), Other(0),
}