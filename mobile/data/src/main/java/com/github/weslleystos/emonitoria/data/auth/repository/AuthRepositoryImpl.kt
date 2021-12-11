package com.github.weslleystos.emonitoria.data.auth.repository

import com.github.weslleystos.emonitoria.data.mappers.mapperToAuthUser
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthException
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebase: FirebaseAuth) : AuthRepository {
    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<AuthUser> {
        return try {
            val authResult = firebase.signInWithEmailAndPassword(email, password).await()
            val authUser = authResult.user!!.mapperToAuthUser()

            Timber.d("Auth successful: %s", (authUser.email ?: authUser.id))
            Resource.success(authUser)
        } catch (throwable: FirebaseAuthException) {
            Timber.e("Auth failure: %s", throwable.message)
            Resource.failure(AuthException(throwable))
        }
    }
}