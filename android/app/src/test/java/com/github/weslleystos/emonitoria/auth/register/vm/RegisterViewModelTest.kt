package com.github.weslleystos.emonitoria.auth.register.vm

import app.cash.turbine.test
import com.github.weslleystos.emonitoria.FakeAuthRepository
import com.github.weslleystos.emonitoria.MainCoroutineRule
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.usecase.RegisterUseCase
import com.github.weslleystos.emonitoria.domain.auth.usecase.UpdateProfileUseCase
import com.github.weslleystos.emonitoria.domain.shared.exceptions.RegisterException
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.domain.shared.model.State.FAILURE
import com.github.weslleystos.emonitoria.domain.shared.model.State.SUCCESS
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RegisterViewModelTest {
    @get:Rule
    val mainCoroutine = MainCoroutineRule()

    @RelaxedMockK
    lateinit var updateProfileUseCase: UpdateProfileUseCase

    @RelaxedMockK
    lateinit var authUser: AuthUser

    private lateinit var viewModelTest: RegisterViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        viewModelTest = RegisterViewModel(
            RegisterUseCase(updateProfileUseCase, FakeAuthRepository())
        )
    }

    @Test
    fun `should be able to register a user with valid params`() = runBlockingTest {
        coEvery { authUser.name } returns "User Test"
        coEvery { updateProfileUseCase(name = "User Test") } returns Resource.success(authUser)

        viewModelTest.doRegister("User Test", "user@email.com", "12345678")

        viewModelTest.state.test {
            val result = awaitItem()
            assertThat(result.state).isEqualTo(SUCCESS)
            assertThat(result.data?.name).isEqualTo("User Test")
        }
    }

    @Test
    fun `shouldn't be able to register an existing user`() = runBlockingTest {
        coEvery { updateProfileUseCase(name = "User Test") } returns Resource.success(authUser)

        viewModelTest.doRegister("User Test", "test@email.com", "12345678")

        viewModelTest.state.test {
            val result = awaitItem()
            assertThat(result.state).isEqualTo(FAILURE)
            assertThat(result.throwable).isInstanceOf(RegisterException::class.java)
        }
    }
}