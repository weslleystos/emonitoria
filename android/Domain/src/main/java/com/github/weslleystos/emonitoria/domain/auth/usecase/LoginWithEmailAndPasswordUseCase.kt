package com.github.weslleystos.emonitoria.domain.auth.usecase

import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import javax.inject.Inject

class LoginWithEmailAndPasswordUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String): Resource<AuthUser> {
        return repository.loginWithEmailAndPassword(email, password)
    }
}