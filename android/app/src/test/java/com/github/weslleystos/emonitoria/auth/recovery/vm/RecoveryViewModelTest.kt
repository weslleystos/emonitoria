package com.github.weslleystos.emonitoria.auth.recovery.vm

import app.cash.turbine.test
import com.github.weslleystos.emonitoria.FakeAuthRepository
import com.github.weslleystos.emonitoria.TestCounterIdlingResource
import com.github.weslleystos.emonitoria.TestDispatchers
import com.github.weslleystos.emonitoria.domain.auth.usecase.RecoveryPasswordUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.State.FAILURE
import com.github.weslleystos.emonitoria.domain.shared.model.State.SUCCESS
import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class RecoveryViewModelTest {
    private lateinit var recoveryViewModel: RecoveryViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        recoveryViewModel = RecoveryViewModel(
            RecoveryPasswordUseCase(FakeAuthRepository()),
            TestCounterIdlingResource(),
            TestDispatchers()
        )
    }

    @Test
    fun `shouldnt be successful sent email to exists user`() = runBlockingTest {
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