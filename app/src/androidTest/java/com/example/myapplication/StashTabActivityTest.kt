package com.example.myapplication

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HelloWorldEspressoTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun loadITabButtonIsDisplayed() {
        onView(withId(R.id.button)).check(matches(isDisplayed()))

        onView(withText("T2 (Remove-only)")).check(doesNotExist())
    }

    @Test
    fun loadButton_WhenClicked_ShouldLoadStashTab() {
        onView(withText(R.id.button)).check(matches(isDisplayed()))

        // Required when all tests run
        // Probably that the clickHandler is not correctly bound on ActivityCreated but it needs more investigation
        Thread.sleep(500)
        onView(withId(R.id.button)).perform(click())

        // Assert that the first item in the list if visible
        onView(withText("T2 (Remove-only)")).check(matches(isDisplayed()))
    }
}