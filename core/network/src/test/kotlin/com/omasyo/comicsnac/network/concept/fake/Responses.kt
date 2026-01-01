package com.omasyo.comicsnac.network.concept.fake

import java.io.File

internal const val root = "src/test/kotlin/com/omasyo/comicsnac/network/concept/fake/"

internal val OdinForceDetailsResponse =
    File(root, "OdinForceDetailsResponse.json").readText()

internal val ConceptsResponse =
    File(root, "ConceptsResponse.json").readText()