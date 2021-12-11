package com.github.weslleystos.emonitoria.data.mappers

import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthException
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

suspend fun FirebaseUser.mapperToAuthUser(): AuthUser {
    val idToken = (getIdToken(false).await()).token
        ?: throw  AuthException("Token not provided")

    return AuthUser(uid, idToken, displayName, email, providerId, isEmailVerified, photoUrl)
}