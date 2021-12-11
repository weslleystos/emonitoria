package com.github.weslleystos.emonitoria.domain.auth.model

import android.net.Uri

class AuthUser(
    id: String,
    val idToken: String,
    name: String?,
    email: String?,
    val providerId: String,
    val isVerified: Boolean,
    avatar: Uri?
) : User(id = id, name = name, email = email, avatar = avatar)