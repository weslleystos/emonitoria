package com.github.weslleystos.emonitoria.auth.login.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.github.weslleystos.emonitoria.FakeAuthRepository
import com.github.weslleystos.emonitoria.domain.auth.usecase.LoginWithEmailAndPasswordUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.State.FAILURE
import com.github.weslleystos.emonitoria.domain.shared.model.State.SUCCESS
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class LoginViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    @Before
    fun setUp() {
        viewModel = LoginViewModel(LoginWithEmailAndPasswordUseCase(FakeAuthRepository()))
    }

    @Test
    fun `should be successful login with valid email and password`() = runBlockingTest {
        viewModel.doLogin("test@email.com", "12345678")

        viewModel.state.test {
            assertThat(awaitItem().state).isEqualTo(SUCCESS)
        }
    }

    @Test
    fun `shouldn't be successful login with invalid email`() = runBlockingTest {
        viewModel.doLogin("test@emai", "12345678")

        viewModel.state.test {
            assertThat(awaitItem().state).isEqualTo(FAILURE)
        }
    }

    @Test
    fun `shouldn't be successful login when doesn't exist a user`() = runBlockingTest {
        viewModel.doLogin("test2@email.com", "12345678")

        viewModel.state.test {
            assertThat(awaitItem().state).isEqualTo(FAILURE)
        }
    }
}