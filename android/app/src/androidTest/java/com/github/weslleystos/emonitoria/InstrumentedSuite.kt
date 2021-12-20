package com.github.weslleystos.emonitoria

import com.github.weslleystos.emonitoria.auth.login.ui.LoginFragmentTest
import com.github.weslleystos.emonitoria.auth.recovery.ui.RecoveryFragmentTest
import com.github.weslleystos.emonitoria.auth.register.ui.RegisterFragmentTest
import com.github.weslleystos.emonitoria.splash.ui.SplashFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    SplashFragmentTest::class,
    LoginFragmentTest::class,
    RecoveryFragmentTest::class,
    RegisterFragmentTest::class
)
class InstrumentedSuite