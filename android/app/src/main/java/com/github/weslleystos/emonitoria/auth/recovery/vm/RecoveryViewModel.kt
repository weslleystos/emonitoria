package com.github.weslleystos.emonitoria.auth.recovery.vm

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.weslleystos.emonitoria.domain.auth.usecase.RecoveryPasswordUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.loadingHandler
import com.github.weslleystos.emonitoria.shared.util.wrapperIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RecoveryViewModel @Inject constructor(
    private val recoveryPasswordUseCase: RecoveryPasswordUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<Boolean>>(Resource.init())
    val state: StateFlow<Resource<Boolean>> = _state

    var email: String? = null

    val isValidEmail = ObservableBoolean(false)
    val isLoading = ObservableBoolean(false)

    fun wrapperDoRecovery(_email: String) = wrapperIdlingResource(viewModelScope) {
        doRecovery(_email)
    }

    suspend fun doRecovery(_email: String) = loadingHandler(isLoading) {
        _state.emit(recoveryPasswordUseCase(_email))
    }
}