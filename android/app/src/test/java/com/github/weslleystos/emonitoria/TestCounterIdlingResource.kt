package com.github.weslleystos.emonitoria

import com.github.weslleystos.emonitoria.shared.util.EspressoCounterIdlingResource

class TestCounterIdlingResource : EspressoCounterIdlingResource {
    override fun increment() {}

    override fun decrement() {}
}