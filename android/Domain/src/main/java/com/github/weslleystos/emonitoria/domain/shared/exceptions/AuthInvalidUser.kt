package com.github.weslleystos.emonitoria.domain.shared.exceptions

class AuthInvalidUser(message: String? = null, cause: Throwable? = null) :
    Throwable(message, cause) {
    constructor(message: String?) : this(message, null)
    constructor(cause: Throwable?) : this(cause?.toString(), cause)
}