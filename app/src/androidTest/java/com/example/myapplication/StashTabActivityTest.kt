package com.example.myapplication

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import wiremock.org.apache.commons.io.IOUtils
import java.nio.charset.StandardCharsets

@RunWith(AndroidJUnit4::class)
@LargeTest
class StashTabActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get:Rule
    val server: WireMockRule = WireMockRule(WireMockConfiguration().dynamicPort())

    @Before
    fun setup() {
        val league = "standard"
        val accountName = "mathil"

        val requestFixture = HttpFixture.getStashItemRequest(server, league, accountName);

        var input = InstrumentationRegistry.getInstrumentation().context.assets.open("tabs_response.json")
        var jsonResponse = IOUtils.toString(input, StandardCharsets.UTF_8.name());
        server.stubFor(requestFixture.willReturn(HttpFixture.getStashItemResponse(jsonResponse)));
    }

    @Test
    fun loadITabButtonIsDisplayed() {
        onView(withId(R.id.button)).check(matches(isDisplayed()))

        onView(withText("T2 (Remove-only)")).check(doesNotExist())
    }

    @Test
    fun loadButton_WhenClicked_ShouldLoadStashTab() {
        onView(withId(R.id.button)).check(matches(isDisplayed()))

        // Required when all tests run
        // Probably that the clickHandler is not correctly bound on ActivityCreated but it needs more investigation
        Thread.sleep(500)
        onView(withId(R.id.button)).perform(click())

        // Assert that the first item in the list if visible
        onView(withText("T2 (Remove-only)")).check(matches(isDisplayed()))
    }
}