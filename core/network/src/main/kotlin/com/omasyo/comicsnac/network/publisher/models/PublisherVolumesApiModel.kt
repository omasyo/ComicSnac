package com.omasyo.comicsnac.network.publisher.models


import com.omasyo.comicsnac.network.common.models.VolumeApiModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublisherVolumesApiModel(
    @SerialName("volumes")
    val volumes: List<VolumeApiModel>
)