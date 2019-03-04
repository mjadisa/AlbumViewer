package com.example.albumviewer

import android.support.test.espresso.matcher.BoundedMatcher
import android.support.v7.widget.RecyclerView
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher

class CustomMatchers {
    companion object {
        fun withItemCount(count: Int): Matcher<View> {
            return object: BoundedMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun describeTo(description: Description?) {
                    description?.appendText("Item count is: $count")
                }

                override fun matchesSafely(recyclerView: RecyclerView?): Boolean {
                    return recyclerView?.adapter?.itemCount == count
                }

            }
        }
    }
}