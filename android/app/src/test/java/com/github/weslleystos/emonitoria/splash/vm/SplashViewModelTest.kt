package com.github.weslleystos.emonitoria.splash.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.repository.AuthRepository
import com.github.weslleystos.emonitoria.domain.auth.usecase.GetAuthenticateUserUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.domain.shared.model.State.FAILURE
import com.github.weslleystos.emonitoria.domain.shared.model.State.SUCCESS
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class SplashViewModelTest {
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @MockK
    lateinit var authRepository: AuthRepository

    @MockK
    lateinit var authUser: AuthUser

    private lateinit var viewModel: SplashViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModel = SplashViewModel(GetAuthenticateUserUseCase(authRepository))
    }


    @Test
    fun `should be failure when dont exists user`() = runBlockingTest {
        coEvery { authRepository.getAuthenticateUser() } returns Resource.failure(Throwable("Token not Provided"))

        viewModel.getAuthenticateUser()

        viewModel.state.test {
            assertThat(awaitItem().state).isEqualTo(FAILURE)
        }
    }

    @Test
    fun `should be success when exists user`() = runBlockingTest {
        coEvery { authRepository.getAuthenticateUser() } returns Resource.success(authUser)

        viewModel.getAuthenticateUser()

        viewModel.state.test {
            assertThat(awaitItem().state).isEqualTo(SUCCESS)
        }
    }
}