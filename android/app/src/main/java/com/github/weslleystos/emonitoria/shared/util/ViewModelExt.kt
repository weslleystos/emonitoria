package com.github.weslleystos.emonitoria.shared.util

import androidx.databinding.ObservableBoolean
import kotlinx.coroutines.*

/**
 * I needed create this wrapper because issue below:
 * @see Issue https://github.com/Kotlin/kotlinx.coroutines/issues/242
 */
inline fun wrapperIdlingResource(
    coroutineScope: CoroutineScope,
    dispatcher: CoroutineDispatcher = Dispatchers.Default,
    crossinline action: suspend () -> Unit
) = coroutineScope.launch(dispatcher) {
    EspressoIdlingResource.increment()
    action()
    EspressoIdlingResource.decrement()
}

suspend inline fun loadingHandler(
    isLoading: ObservableBoolean,
    crossinline handler: suspend () -> Unit
) {
    isLoading.set(true)
    handler()
    isLoading.set(false)
}