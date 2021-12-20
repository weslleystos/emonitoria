package com.github.weslleystos.emonitoria.util

import androidx.test.espresso.idling.CountingIdlingResource
import com.github.weslleystos.emonitoria.shared.util.EspressoCounterIdlingResource

object EspressoCounterIdlingResource : EspressoCounterIdlingResource {
    @JvmField
    val countingIdlingResource = CountingIdlingResource("TEST")

    override fun increment() {
        countingIdlingResource.increment()
    }

    override fun decrement() {
        if (!countingIdlingResource.isIdleNow) {
            countingIdlingResource.decrement()
        }
    }
}