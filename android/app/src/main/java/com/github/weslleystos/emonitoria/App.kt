package com.github.weslleystos.emonitoria

import android.app.Application
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize timber
        Timber.plant(Timber.DebugTree())
    }

    companion object {
        lateinit var authUser: AuthUser
    }
}