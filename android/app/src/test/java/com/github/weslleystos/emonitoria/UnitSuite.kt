package com.github.weslleystos.emonitoria

import com.github.weslleystos.emonitoria.auth.login.vm.LoginViewModelTest
import com.github.weslleystos.emonitoria.auth.recovery.vm.RecoveryViewModelTest
import com.github.weslleystos.emonitoria.auth.register.vm.RegisterViewModelTest
import com.github.weslleystos.emonitoria.shared.util.ValidatorStringTest
import com.github.weslleystos.emonitoria.splash.vm.SplashViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    ValidatorStringTest::class,
    SplashViewModelTest::class,
    LoginViewModelTest::class,
    RecoveryViewModelTest::class,
    RegisterViewModelTest::class
)
class UnitSuite