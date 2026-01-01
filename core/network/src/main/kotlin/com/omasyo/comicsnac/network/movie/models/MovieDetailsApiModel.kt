package com.omasyo.comicsnac.network.movie.models


import com.omasyo.comicsnac.network.common.models.CharacterApiModel
import com.omasyo.comicsnac.network.common.models.ImageApiModel
import com.omasyo.comicsnac.network.common.models.LocationApiModel
import com.omasyo.comicsnac.network.common.models.ObjectApiModel
import com.omasyo.comicsnac.network.common.models.PersonApiModel
import com.omasyo.comicsnac.network.common.models.StudioApiModel
import com.omasyo.comicsnac.network.common.models.TeamApiModel
import com.omasyo.comicsnac.network.common.serializers.DateAsStringSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime

@Serializable
data class MovieDetailsApiModel(
    @SerialName("api_detail_url") val apiDetailUrl: String,
    @SerialName("box_office_revenue") val boxOfficeRevenue: String?,
    @SerialName("budget") val budget: String?,
    @SerialName("characters") val characters: List<CharacterApiModel>,
    @SerialName("deck") val deck: String?,
    @SerialName("description") val description: String?,
    @SerialName("id") val id: Int,
    @SerialName("image") val image: ImageApiModel,
    @SerialName("locations") val locations: List<LocationApiModel>,
    @SerialName("name") val name: String,
    @SerialName("objects") val objects: List<ObjectApiModel>,
    @SerialName("producers") val producers: List<PersonApiModel>?,
    @SerialName("rating") val rating: String,

    @Serializable(DateAsStringSerializer::class)
    @SerialName("release_date") val releaseDate: LocalDateTime,

    @SerialName("runtime") val runtime: String,
    @SerialName("site_detail_url") val siteDetailUrl: String,
    @SerialName("studios") val studios: List<StudioApiModel>?,
    @SerialName("teams") val teams: List<TeamApiModel>,
    @SerialName("total_revenue") val totalRevenue: String?,
    @SerialName("writers") val writers: List<PersonApiModel>?
)