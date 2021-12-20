package com.github.weslleystos.emonitoria.auth.recovery.vm

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.weslleystos.emonitoria.domain.auth.usecase.RecoveryPasswordUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.DispatcherProvider
import com.github.weslleystos.emonitoria.shared.util.EspressoCounterIdlingResource
import com.github.weslleystos.emonitoria.shared.util.on
import com.github.weslleystos.emonitoria.shared.util.wrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecoveryViewModel @Inject constructor(
    private val recoveryPasswordUseCase: RecoveryPasswordUseCase,
    private val idlingResource: EspressoCounterIdlingResource,
    private val dispatchers: DispatcherProvider
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<Boolean>>(Resource.starting())
    val state: StateFlow<Resource<Boolean>> = _state

    var email: String? = null

    val isValidEmail = ObservableBoolean(false)
    val isLoading = ObservableBoolean(false)

    fun doRecovery(_email: String) = viewModelScope.launch(dispatchers.default) {
        idlingResource.wrapper {
            isLoading.on {
                _state.emit(recoveryPasswordUseCase(_email))
            }
        }
    }
}