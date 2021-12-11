package com.github.weslleystos.emonitoria.auth.di

import com.github.weslleystos.emonitoria.data.auth.di.AuthModule
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.auth.usecase.LoginWithEmailAndPasswordUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.testing.TestInstallIn
import java.com.github.weslleystos.emonitoria.FakeAuthRepository

@TestInstallIn(
    components = [ViewModelComponent::class],
    replaces = [AuthModule::class]
)
@Module
object TestAuthModule {
    @Provides
    fun provideLoginWithEmailAndPasswordUseCase(authRepository: AuthRepository): LoginWithEmailAndPasswordUseCase {
        return LoginWithEmailAndPasswordUseCase(authRepository)
    }

    @Provides
    fun provideAuthRepository(): AuthRepository {
        return FakeAuthRepository()
    }
}