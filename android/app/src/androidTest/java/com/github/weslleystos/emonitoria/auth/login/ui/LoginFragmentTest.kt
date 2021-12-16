package com.github.weslleystos.emonitoria.auth.login.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@UninstallModules(AuthModule::class)
@HiltAndroidTest
class LoginFragmentTest {
    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val idlingResourceRule = EspressoIdlingResourceRule()

    @BindValue
    val authRepository: AuthRepository = FakeAuthRepository()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun should_be_successful_login_with_valid_email_and_password() {
        launchFragmentInHiltContainer<LoginFragment>()

        onView(withId(R.id.input_email)).perform(typeText("test@email.com"))

        onView(withId(R.id.input_password)).perform(typeText("12345678"))

        onView(withId(R.id.btn_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("Success")))
    }

    @Test
    fun shouldnt_be_able_to_try_to_login_with_invalid_email() {
        launchFragmentInHiltContainer<LoginFragment>()

        onView(withId(R.id.input_email)).perform(typeText("test@email"))

        onView(withId(R.id.input_password)).perform(typeText("12345678"))

        onView(withId(R.id.btn_sign_in)).check(matches(not(isEnabled())))
    }

    @Test
    fun shouldnt_be_able_to_try_to_login_with_invalid_password() {
        launchFragmentInHiltContainer<LoginFragment>()

        onView(withId(R.id.input_email)).perform(typeText("test@email.com"))

        onView(withId(R.id.input_password)).perform(typeText("1234567"))

        onView(withId(R.id.btn_sign_in)).check(matches(not(isEnabled())))
    }

    @Test
    fun shouldnt_be_successful_login_with_invalid_password() {
        launchFragmentInHiltContainer<LoginFragment>()

        onView(withId(R.id.input_email)).perform(typeText("test@email.com"))

        onView(withId(R.id.input_password)).perform(typeText("123456789"))

        onView(withId(R.id.btn_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("E-mail e/ou senha inválido")))
    }

    @Test
    fun shouldnt_be_successful_login_if_not_exist_a_user() {
        launchFragmentInHiltContainer<LoginFragment>()

        onView(withId(R.id.input_email)).perform(typeText("test2@email.com"))

        onView(withId(R.id.input_password)).perform(typeText("12345678"))

        onView(withId(R.id.btn_sign_in)).perform(click())

        onView(withId(com.google.android.material.R.id.snackbar_text))
            .check(matches(withText("E-mail e/ou senha inválido")))
    }
}