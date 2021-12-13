package com.github.weslleystos.emonitoria.domain.auth.repository

import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.shared.model.Resource

interface AuthRepository {
    suspend fun loginWithEmailAndPassword(email: String, password: String): Resource<AuthUser>

    suspend fun getAuthenticateUser(): Resource<AuthUser>
}