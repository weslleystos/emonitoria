package com.github.weslleystos.emonitoria.domain.shared.model

import com.github.weslleystos.emonitoria.domain.shared.model.State.*

class Resource<T>(val state: State, val data: T? = null, val throwable: Throwable? = null) {
    companion object {
        fun <T> init(): Resource<T> = Resource(INIT)

        fun <T> loading(): Resource<T> = Resource(LOADING)

        fun <T> loaded(): Resource<T> = Resource(LOADED)

        fun <T> success(data: T): Resource<T> = Resource(SUCCESS, data)

        fun <T> failure(throwable: Throwable?): Resource<T> = Resource(FAILURE, null, throwable)
    }
}

enum class State {
    INIT,
    SUCCESS,
    FAILURE,
    LOADING,
    LOADED,
}

inline fun <T> Resource<T>.onLoading(action: () -> Unit): Resource<T> {
    if (state == LOADING) action()
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

inline fun <T> Resource<T>.onLoaded(action: () -> Unit): Resource<T> {
    if (state == LOADED) action()
    return this
}