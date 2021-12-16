package com.github.weslleystos.emonitoria.data.auth.di

import com.github.weslleystos.emonitoria.data.auth.repository.AuthRepositoryImpl
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.auth.usecase.GetAuthenticateUserUseCase
import com.github.weslleystos.emonitoria.domain.auth.usecase.LoginWithEmailAndPasswordUseCase
import com.github.weslleystos.emonitoria.domain.auth.usecase.RecoveryPasswordUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {
    @Provides
    @Singleton
    fun providesRecoveryPasswordUserUseCase(authRepository: AuthRepository): RecoveryPasswordUseCase {
        return RecoveryPasswordUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun providesGetAuthenticateUserUseCase(authRepository: AuthRepository): GetAuthenticateUserUseCase {
        return GetAuthenticateUserUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideLoginWithEmailAndPasswordUseCase(authRepository: AuthRepository): LoginWithEmailAndPasswordUseCase {
        return LoginWithEmailAndPasswordUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(firebaseAuth: FirebaseAuth): AuthRepository {
        return AuthRepositoryImpl(firebaseAuth)
    }

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
}