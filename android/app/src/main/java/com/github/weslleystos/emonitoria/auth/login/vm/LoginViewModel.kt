package com.github.weslleystos.emonitoria.auth.login.vm

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.usecase.LoginWithEmailAndPasswordUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.loadingHandler
import com.github.weslleystos.emonitoria.shared.util.wrapperIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithEmailAndPassword: LoginWithEmailAndPasswordUseCase
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<AuthUser>>(Resource.loading())
    val state: StateFlow<Resource<AuthUser>> = _state

    var email: String? = null
    var password: String? = null

    val isValidEmail = ObservableBoolean(false)
    val isValidPassWord = ObservableBoolean(false)
    val isLoading = ObservableBoolean(false)

    fun wrapperDoLogin(_email: String, _password: String) = wrapperIdlingResource(viewModelScope) {
        doLogin(_email, _password)
    }

    suspend fun doLogin(_email: String, _password: String) = loadingHandler(isLoading) {
        _state.emit(loginWithEmailAndPassword(_email, _password))
    }
}

