package com.example.myapplication

import HttpFixture.getStashItemResponse
import com.example.myapplication.model.dto.StashTabItemResponse
import com.example.myapplication.repository.getPOEService
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.nio.file.Files


class POEServiceUnitTest {

    @get:Rule val server: WireMockRule = WireMockRule(WireMockConfiguration().port(8080))

    @Test
    fun stashTabTest() {
        val league = "standard"
        val accountName = "mathil"

        val requestFixture = HttpFixture.getStashItemRequest(server, league, accountName);

        val classLoader = javaClass.classLoader
        val file = File(classLoader.getResource("tabs_response.json").file)
        val jsonResponse = String(Files.readAllBytes(file.toPath()));

        server.stubFor(requestFixture.willReturn(getStashItemResponse(jsonResponse)));

        val service = getPOEService()

        val response = service.getStashTabs(league, accountName)?.execute()

        Assert.assertEquals(200, response?.code())

        val body = response?.body()
        Assert.assertEquals(281, body?.numTabs)
        Assert.assertEquals(281, body?.tabs?.size)

        // Assert that "StashTabItemResponse" gets correctly mapped
        val tab = body?.tabs?.get(0)
        Assert.assertEquals(StashTabItemResponse(n = "T2 (Remove-only)", i = 0), tab)
    }
}