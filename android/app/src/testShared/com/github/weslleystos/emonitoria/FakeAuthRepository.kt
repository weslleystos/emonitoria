package com.github.weslleystos.emonitoria

import android.net.Uri
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthException
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthInvalidUser
import com.github.weslleystos.emonitoria.domain.shared.exceptions.RegisterException
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.*
import javax.inject.Inject

class FakeAuthRepository @Inject constructor() : AuthRepository {
    private val dataset = mutableListOf(
        AuthUser(
            "123421", "token-123", "Test", "test@email.com", "Email", true, null
        )
    )

    override suspend fun loginWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<AuthUser> {
        return when {
            !ValidatorString(email)
                .isNotEmpty()
                .isEmail()
                .isValid() -> Resource.failure(AuthException("E-mail e/ou senha inválido"))

            !ValidatorString(password)
                .isNotEmpty()
                .isGreatThan(7)
                .isLessThan(15)
                .isValid() -> Resource.failure(AuthException("E-mail e/ou senha inválido"))

            dataset.find { user -> user.email == email && password == "12345678" } == null ->
                Resource.failure(AuthException("E-mail e/ou senha inválido"))

            else -> Resource.success(dataset[0])
        }
    }

    override suspend fun getAuthenticateUser(): Resource<AuthUser> {
        return Resource.failure(AuthException("AuthUser not provided"))
    }

    override suspend fun recoveryPassword(email: String): Resource<Boolean> {
        return when {
            dataset.find { user -> user.email == email } == null ->
                Resource.failure(AuthInvalidUser())

            else -> Resource.success(true)
        }
    }

    override suspend fun registerWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<AuthUser> {
        return when {
            dataset.find { user -> user.email == email } != null -> Resource.failure(
                RegisterException("Usuário já existente!")
            )
            else -> {
                val user = AuthUser("", "", "", email, "Email", true, null)
                dataset.add(user)

                Resource.success(user)
            }
        }
    }

    override suspend fun updateProfile(name: String?, photo: Uri?): Resource<AuthUser> {
        return Resource.success(dataset[0])
    }
}