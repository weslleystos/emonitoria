package com.github.weslleystos.emonitoria.domain.auth.usecase

import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.domain.shared.model.State.SUCCESS
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val updateProfileUseCase: UpdateProfileUseCase,
    private val authRepository: AuthRepository,
) {
    suspend operator fun invoke(name: String, email: String, password: String): Resource<AuthUser> {
        val result = authRepository.registerWithEmailAndPassword(email, password)
        return if (result.state == SUCCESS) {
            updateProfileUseCase(name = name)
        } else {
            result
        }
    }
}