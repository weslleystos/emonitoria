package com.github.weslleystos.emonitoria.shared.util

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

/**
 * I needed create this wrapper because issue below:
 * @see Issue https://github.com/Kotlin/kotlinx.coroutines/issues/242
 */
inline fun ViewModel.wrapperIdlingResource(
    counterIdlingResource: EspressoCounterIdlingResource,
    dispatchers: DispatcherProvider,
    crossinline action: suspend () -> Unit
) = viewModelScope.launch(dispatchers.default) {
    counterIdlingResource.increment()
    action()
    counterIdlingResource.decrement()
}

suspend inline fun EspressoCounterIdlingResource.wrapper(
    crossinline action: suspend () -> Unit
) {
    increment()
    action()
    decrement()
}


inline fun ViewModel.wrapperIdlingResource(
    counterIdlingResource: EspressoCounterIdlingResource,
    crossinline action: suspend () -> Unit
) = viewModelScope.launch(Dispatchers.Default) {
    counterIdlingResource.increment()
    action()
    counterIdlingResource.decrement()
}


suspend inline fun ObservableBoolean.on(
    crossinline handler: suspend () -> Unit
) {
    set(true)
    handler()
    set(false)
}