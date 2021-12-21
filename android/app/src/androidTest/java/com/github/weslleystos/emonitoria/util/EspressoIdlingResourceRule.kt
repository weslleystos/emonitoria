package com.github.weslleystos.emonitoria.util

import androidx.test.espresso.IdlingRegistry
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class EspressoIdlingResourceRule : TestWatcher() {
    private val idlingResource = EspressoCounterIdlingResource.countingIdlingResource

    override fun starting(description: Description?) {
        IdlingRegistry.getInstance().register(idlingResource)
        super.starting(description)
    }

    override fun finished(description: Description?) {
        IdlingRegistry.getInstance().unregister(idlingResource)
        super.finished(description)
    }
}