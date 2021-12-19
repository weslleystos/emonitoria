package com.github.weslleystos.emonitoria.auth.register.vm

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.usecase.RegisterUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.loadingHandler
import com.github.weslleystos.emonitoria.shared.util.wrapperIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<AuthUser>>(Resource.starting())
    val state: StateFlow<Resource<AuthUser>> = _state

    var name: String? = null
    var email: String? = null
    var password: String? = null
    var confirmPassword: String? = null

    val isValidEmail = ObservableBoolean(false)
    val isValidPassWord = ObservableBoolean(false)
    val isValidConfirmPassWord = ObservableBoolean(false)
    val isLoading = ObservableBoolean(false)

    fun wrapperDoRegister(
        _name: String,
        _email: String,
        _password: String
    ) = wrapperIdlingResource(viewModelScope) {
        doRegister(_name, _email, _password)
    }

    suspend fun doRegister(
        _name: String,
        _email: String,
        _password: String
    ) = loadingHandler(isLoading) {
        _state.emit(registerUseCase(_name, _email, _password))
    }
}