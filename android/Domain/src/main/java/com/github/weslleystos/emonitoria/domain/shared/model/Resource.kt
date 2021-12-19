package com.github.weslleystos.emonitoria.domain.shared.model

import com.github.weslleystos.emonitoria.domain.shared.model.State.*

class Resource<T>(val state: State, val data: T? = null, val throwable: Throwable? = null) {
    companion object {
        fun <T> init(): Resource<T> = Resource(INIT)

        fun <T> starting(): Resource<T> = Resource(START)

        fun <T> finished(): Resource<T> = Resource(FINISHED)

        fun <T> success(data: T): Resource<T> = Resource(SUCCESS, data)

        fun <T> failure(throwable: Throwable?): Resource<T> = Resource(FAILURE, null, throwable)
    }
}

enum class State {
    INIT,
    SUCCESS,
    FAILURE,
    START,
    FINISHED,
}

inline fun <T> Resource<T>.onStarting(action: () -> Unit): Resource<T> {
    if (state == START) action()
    return this
}

inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (state == SUCCESS) data?.let(action)
    return this
}

inline fun <T> Resource<T>.onFailure(action: (Throwable) -> Unit): Resource<T> {
    if (state == FAILURE) throwable?.let(action)
    return this
}

inline fun <T> Resource<T>.onFinished(action: () -> Unit): Resource<T> {
    if (state == FINISHED) action()
    return this
}