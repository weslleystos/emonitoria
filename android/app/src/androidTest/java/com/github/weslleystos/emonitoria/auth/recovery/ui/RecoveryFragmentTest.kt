package com.github.weslleystos.emonitoria.auth.recovery.ui

import android.content.res.Resources
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.github.weslleystos.emonitoria.EspressoIdlingResourceRule
import com.github.weslleystos.emonitoria.FakeAuthRepository
import com.github.weslleystos.emonitoria.R
import com.github.weslleystos.emonitoria.data.auth.di.AuthModule
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.launchFragmentInHiltContainer
import dagger.hilt.android.testing.BindValue
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@UninstallModules(AuthModule::class)
@HiltAndroidTest
class RecoveryFragmentTest {
    @get:Rule
    val hilt = HiltAndroidRule(this)

    @get:Rule
    val sequential = InstantTaskExecutorRule()

    @get:Rule
    val idlingResource = EspressoIdlingResourceRule()

    @BindValue
    val authRepository: AuthRepository = FakeAuthRepository()

    @Inject
    lateinit var navController: TestNavHostController

    @Inject
    lateinit var resources: Resources

    @Before
    fun setUp() {
        hilt.inject()
    }

    @Test
    fun should_be_able_to_send_email_to_exists_an_user() {
        launchFragmentInHiltContainer<RecoveryFragment>(navController)

        onView(withId(R.id.input_email))
            .perform(typeText("test@email.com"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_in)).perform(click()).perform()

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(resources.getString(R.string.email_sent))))
    }

    @Test
    fun shouldnt_be_able_to_send_email_to_not_exists_an_user() {
        launchFragmentInHiltContainer<RecoveryFragment>(navController)

        onView(withId(R.id.input_email))
            .perform(typeText("test2@email.com"), closeSoftKeyboard())

        onView(withId(R.id.btn_sign_in)).perform(click()).perform()

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText(resources.getString(R.string.invalid_user))))
    }
}