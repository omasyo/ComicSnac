package com.omasyo.comicsnac.data.power

import com.omasyo.comicsnac.model.power.PowerBasic
import com.omasyo.comicsnac.model.power.PowerDetails
import com.omasyo.comicsnac.network.common.models.PowerApiModel
import com.omasyo.comicsnac.network.power.models.PowerDetailsApiModel
import com.omasyo.comicsnac.network.power.models.PowerListApiModel

internal fun List<PowerApiModel>.toBasic() = map { apiModel -> apiModel.toPowerBasic() }

internal fun PowerApiModel.toPowerBasic() = PowerBasic(
    apiDetailUrl = apiDetailUrl, id = id, name = name
)

internal fun List<PowerListApiModel>.toPowers() = map { apiModel -> apiModel.toPower() }

internal fun PowerListApiModel.toPower() = PowerBasic(
    apiDetailUrl = apiDetailUrl, id = id, name = name
)

internal fun PowerDetailsApiModel.toPowerDetails() =
    PowerDetails(
        apiDetailUrl = apiDetailUrl,
        characterIds = characters.map { it.id },
        description = description ?: "",
        id = id,
        name = name,
        siteDetailUrl = siteDetailUrl
    )