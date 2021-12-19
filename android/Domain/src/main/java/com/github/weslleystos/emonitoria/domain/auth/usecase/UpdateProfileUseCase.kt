package com.github.weslleystos.emonitoria.domain.auth.usecase

import android.net.Uri
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import javax.inject.Inject

class UpdateProfileUseCase @Inject constructor(private val authRepository: AuthRepository) {
    suspend operator fun invoke(name: String? = null, photo: Uri? = null): Resource<AuthUser> {
        return authRepository.updateProfile(name, photo)
    }
}