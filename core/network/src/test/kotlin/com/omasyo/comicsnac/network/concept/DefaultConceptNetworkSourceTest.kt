package com.omasyo.comicsnac.network.concept

import com.omasyo.comicsnac.network.NetworkSourceTest
import com.omasyo.comicsnac.network.concept.fake.ConceptsResponse
import com.omasyo.comicsnac.network.concept.fake.OdinForceDetailsResponse
import io.ktor.client.request.HttpRequestData
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DefaultConceptNetworkSourceTest : NetworkSourceTest<ConceptNetworkSource>() {

    override fun generateResponseBody(request: HttpRequestData) = when (request.url.encodedPath) {
        "/api/concept/4015-35070" -> OdinForceDetailsResponse
        "/api/concepts" -> ConceptsResponse
        else -> throw NotImplementedError("Invalid Url Path: ${request.url.encodedPath}")
    }

    @Before
    override fun initNetworkSource() {
        networkSource = DefaultConceptNetworkSource(client)
    }

    @Test
    fun getConceptDetails() = runTest {
        val response =
            networkSource.getConceptDetails(apiKey, "35070")
        assertEquals("The Odin Force", response.getOrNull()?.results?.name)
    }

    @Test
    fun getAllConcepts() = runTest {
        val response =
            networkSource.getAllConcepts(apiKey, 100, 0)
        val concepts = response.getOrThrow().results
        assert(concepts.any { it.name == "The Odin Force" })
        assertEquals(100, concepts.size)

    }
}