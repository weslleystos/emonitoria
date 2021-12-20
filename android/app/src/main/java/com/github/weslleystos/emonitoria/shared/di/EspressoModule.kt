package com.github.weslleystos.emonitoria.shared.di

import com.github.weslleystos.emonitoria.shared.util.EspressoCounterIdlingResource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object EspressoModule {

    @Singleton
    @Provides
    fun provideCounterIdlingResource(): EspressoCounterIdlingResource {
        return object : EspressoCounterIdlingResource {
            override fun increment() {}

            override fun decrement() {}
        }
    }
}