package com.irfanirawansukirman.codebasemodularity

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.irfanirawansukirman.codebasemodularity.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainActivityTest {

    @get:Rule // global set for activity test
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun recyclerviewIsDisplayed() {
        onView(withId(R.id.recyclerMain)).check(matches(isDisplayed()))
    }
}
