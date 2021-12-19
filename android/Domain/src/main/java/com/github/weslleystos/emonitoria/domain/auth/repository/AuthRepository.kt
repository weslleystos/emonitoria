package com.github.weslleystos.emonitoria.domain.auth.repository

import android.net.Uri
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.shared.model.Resource

interface AuthRepository {
    suspend fun loginWithEmailAndPassword(email: String, password: String): Resource<AuthUser>

    suspend fun getAuthenticateUser(): Resource<AuthUser>

    suspend fun recoveryPassword(email: String): Resource<Boolean>

    suspend fun registerWithEmailAndPassword(email: String, password: String): Resource<AuthUser>

    suspend fun updateProfile(name: String?, photo: Uri?): Resource<AuthUser>
}