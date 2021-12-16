package com.github.weslleystos.emonitoria.domain.auth.usecase

import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import javax.inject.Inject

class RecoveryPasswordUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(email: String): Resource<Boolean> {
        return authRepository.recoveryPassword(email)
    }
}