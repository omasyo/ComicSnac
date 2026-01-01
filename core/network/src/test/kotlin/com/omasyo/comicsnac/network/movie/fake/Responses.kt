package com.omasyo.comicsnac.network.movie.fake

import java.io.File

internal const val root = "src/test/kotlin/com/omasyo/comicsnac/network/movie/fake/"

internal val MovieDetailsResponse = File(root, "MovieDetailsResponse.json").readText()

internal val MoviesResponse = File(root, "MoviesResponse.json").readText()