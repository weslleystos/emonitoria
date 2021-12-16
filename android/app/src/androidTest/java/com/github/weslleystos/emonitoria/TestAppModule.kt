package com.github.weslleystos.emonitoria

import android.content.Context
import android.content.res.Resources
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object TestAppModule {
    @Singleton
    @Provides
    fun provideNavController(): TestNavHostController {
        return TestNavHostController(ApplicationProvider.getApplicationContext()).apply {
            setGraph(R.navigation.nav_graph)
        }
    }

    @Singleton
    @Provides
    fun provideAndroidResources(): Resources {
        return ApplicationProvider.getApplicationContext<Context>().resources
    }
}