package com.github.weslleystos.emonitoria.auth.di

//@TestInstallIn(
//    components = [SingletonComponent::class],
//    replaces = [AuthModule::class]
//)
//@Module
//object TestAuthModule {
//    @Provides
//    fun providesGetAuthenticateUserUseCase(authRepository: AuthRepository): GetAuthenticateUserUseCase {
//        return GetAuthenticateUserUseCase(authRepository)
//    }
//
//    @Singleton
//    @Provides
//    fun provideLoginWithEmailAndPasswordUseCase(authRepository: AuthRepository): LoginWithEmailAndPasswordUseCase {
//        return LoginWithEmailAndPasswordUseCase(authRepository)
//    }
//
//    @Singleton
//    @Provides
//    fun provideAuthRepository(): AuthRepository {
//        return FakeAuthRepository()
//    }
//}