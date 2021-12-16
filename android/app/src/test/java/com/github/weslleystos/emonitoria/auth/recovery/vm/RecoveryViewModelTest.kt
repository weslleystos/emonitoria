package com.github.weslleystos.emonitoria.auth.recovery.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.github.weslleystos.emonitoria.FakeAuthRepository
import com.github.weslleystos.emonitoria.domain.auth.usecase.RecoveryPasswordUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.State.FAILURE
import com.github.weslleystos.emonitoria.domain.shared.model.State.SUCCESS
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class RecoveryViewModelTest {
    @get:Rule
    val sequential = InstantTaskExecutorRule()

    private lateinit var recoveryViewModel: RecoveryViewModel

    @Before
    fun setUp() {
        recoveryViewModel = RecoveryViewModel(RecoveryPasswordUseCase(FakeAuthRepository()))
    }

    @Test
    fun `should be successful sent email to exists user`() = runBlockingTest {
        recoveryViewModel.doRecovery("test@email.com")

        recoveryViewModel.state.test {
            assertThat(awaitItem().state).isEqualTo(SUCCESS)
        }
    }

    @Test
    fun `shouldn't be able to send email to a user who doesn't exist`() = runBlockingTest {
        recoveryViewModel.doRecovery("test2@email.com")

        recoveryViewModel.state.test {
            assertThat(awaitItem().state).isEqualTo(FAILURE)
        }
    }
}