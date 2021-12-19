package com.github.weslleystos.emonitoria.data.auth.repository

import android.net.Uri
import com.github.weslleystos.emonitoria.data.mappers.mapperToAuthUser
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthException
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthInvalidUser
import com.github.weslleystos.emonitoria.domain.shared.exceptions.RegisterException
import com.github.weslleystos.emonitoria.domain.shared.exceptions.UpdateProfileException
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.ktx.userProfileChangeRequest
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
        } catch (throwable: Throwable) {
            Timber.e("Auth failure: %s", throwable.message)
            Resource.failure(AuthException(throwable))
        }
    }

    override suspend fun getAuthenticateUser(): Resource<AuthUser> {
        return try {
            val authUser = firebase.currentUser!!.mapperToAuthUser()

            Timber.d("Auth successful: %s", (authUser.email ?: authUser.id))
            Resource.success(authUser)
        } catch (throwable: Throwable) {
            Timber.e("Auth failure: %s", throwable.message)
            Resource.failure(AuthException(throwable))
        }
    }

    override suspend fun recoveryPassword(email: String): Resource<Boolean> {
        return try {
            firebase.setLanguageCode("pt")
            firebase.sendPasswordResetEmail(email).await()

            Timber.d("Recovery email sent")
            Resource.success(true)
        } catch (invalidUserException: FirebaseAuthInvalidUserException) {
            Timber.e("Send recovery email failed: %s", invalidUserException.message)
            Resource.failure(AuthInvalidUser(invalidUserException))
        } catch (throwable: Throwable) {
            Timber.e("Send recovery email failed: %s", throwable.message)
            Resource.failure(AuthException(throwable))
        }
    }

    override suspend fun registerWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<AuthUser> {
        return try {
            firebase.createUserWithEmailAndPassword(email, password).await()

            val authUser = firebase.currentUser!!.mapperToAuthUser()

            Timber.d("Register successful: %s", authUser.id)
            Resource.success(authUser)
        } catch (throwable: Throwable) {
            Timber.e("Register failure: %s", throwable.message)
            Resource.failure(RegisterException(throwable))
        }
    }

    override suspend fun updateProfile(name: String?, photo: Uri?): Resource<AuthUser> {
        return try {
            val profileUpdates = userProfileChangeRequest {
                name?.let { displayName = it }
                photo?.let { photoUri = it }
            }

            firebase.currentUser!!.updateProfile(profileUpdates).await()
            val authUser = firebase.currentUser!!.mapperToAuthUser()

            Timber.d("Update profile successful: %s", authUser.id)
            Resource.success(authUser)
        } catch (throwable: Throwable) {
            Timber.e("Update Profile failed: %s", throwable.message)
            Resource.failure(UpdateProfileException(throwable))
        }
    }
}