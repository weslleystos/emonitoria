package com.github.weslleystos.emonitoria.domain.auth.usecase

import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import javax.inject.Inject

class GetAuthenticateUserUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(): Resource<AuthUser> {
        return authRepository.getAuthenticateUser()
    }
}