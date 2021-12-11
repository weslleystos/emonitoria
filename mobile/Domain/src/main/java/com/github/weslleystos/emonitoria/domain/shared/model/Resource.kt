package com.github.weslleystos.emonitoria.domain.shared.model

class Resource<T>(val state: State, val data: T? = null, val throwable: Throwable? = null) {
    companion object {
        fun <T> loading(): Resource<T> = Resource(State.LOADING)

        fun <T> loaded(): Resource<T> = Resource(State.LOADED)

        fun <T> success(data: T): Resource<T> = Resource(State.SUCCESS, data)

        fun <T> failure(throwable: Throwable?): Resource<T> =
            Resource(State.FAILURE, null, throwable)
    }
}

enum class State {
    SUCCESS,
    FAILURE,
    LOADING,
    LOADED,
}

inline fun <T> Resource<T>.onLoading(action: () -> Unit): Resource<T> {
    if (state == State.LOADING) action()
    return this
}

inline fun <T> Resource<T>.onSuccess(action: (T) -> Unit): Resource<T> {
    if (state == State.SUCCESS) data?.let(action)
    return this
}

inline fun <T> Resource<T>.onFailure(action: (Throwable) -> Unit): Resource<T> {
    if (state == State.FAILURE) throwable?.let(action)
    return this
}

inline fun <T> Resource<T>.onLoaded(action: () -> Unit): Resource<T> {
    if (state == State.LOADED) action()
    return this
}