package com.example.albumviewer

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import com.example.albumviewer.common.ALBUM_ENDPOINT
import com.example.albumviewer.common.TEST_BASE_URL
import com.example.albumviewer.ui.home.HomeActivity
import io.appflate.restmock.RESTMockServer
import io.appflate.restmock.utils.RequestMatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class HomeActivityTest {

    @get:Rule
    val activityTestRule = ActivityTestRule<HomeActivity>(HomeActivity::class.java, true, false)


    @Before
    fun setup() {

        TEST_BASE_URL = RESTMockServer.getUrl()
        RESTMockServer.whenGET(RequestMatchers.pathContains(ALBUM_ENDPOINT))
            .thenReturnFile(200, "test_response.json")

        activityTestRule.launchActivity(Intent())
    }

    @Test
    fun test() {
        IdlingRegistry.getInstance().register(activityTestRule.activity.getIdlingResource())
        onView(withId(R.id.rvData)).check(matches(CustomMatchers.withItemCount(10)))
    }

}