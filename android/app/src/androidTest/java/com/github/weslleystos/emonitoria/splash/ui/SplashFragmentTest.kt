package com.github.weslleystos.emonitoria.splash.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.espresso.IdlingRegistry
import com.github.weslleystos.emonitoria.R
import com.github.weslleystos.emonitoria.data.auth.di.AuthModule
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthException
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.launchFragmentInHiltContainer
import com.github.weslleystos.emonitoria.shared.util.EspressoIdlingResource
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(AuthModule::class)
@HiltAndroidTest
class SplashFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @RelaxedMockK
    lateinit var authUser: AuthUser

    @RelaxedMockK
    lateinit var authException: AuthException

    @BindValue
    @RelaxedMockK
    lateinit var authRepository: AuthRepository

    lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        hiltRule.inject()
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)

        navController = TestNavHostController(getApplicationContext()).apply {
            setGraph(R.navigation.nav_graph)
            setCurrentDestination(R.id.splashFragment)
        }
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
    }

    @Test
    fun should_be_redirect_to_signIn_fragment_when_not_exists_authenticate_user() {
        coEvery { authRepository.getAuthenticateUser() } returns Resource.failure(authException)

        launchFragmentInHiltContainer<SplashFragment>(navController)

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.signInFragment)
    }


    @Test
    fun should_be_redirect_to_home_fragment_when_exists_an_authenticate_user() {
        coEvery { authRepository.getAuthenticateUser() } returns Resource.success(authUser)

        launchFragmentInHiltContainer<SplashFragment>(navController)

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.homeFragment)
    }
}
