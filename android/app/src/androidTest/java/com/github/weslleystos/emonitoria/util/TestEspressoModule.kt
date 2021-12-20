package com.github.weslleystos.emonitoria.util

import com.github.weslleystos.emonitoria.shared.di.EspressoModule
import com.github.weslleystos.emonitoria.shared.util.EspressoCounterIdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [EspressoModule::class]
)
object TestEspressoModule {
    @Singleton
    @Provides
    fun provideCounterIdlingResource(): EspressoCounterIdlingResource {
        return EspressoCounterIdlingResource
    }
}