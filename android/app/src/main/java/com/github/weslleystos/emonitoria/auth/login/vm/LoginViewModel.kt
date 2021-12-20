package com.github.weslleystos.emonitoria.auth.login.vm

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.usecase.LoginWithEmailAndPasswordUseCase
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
class LoginViewModel @Inject constructor(
    private val loginWithEmailAndPassword: LoginWithEmailAndPasswordUseCase,
    private val idlingResource: EspressoCounterIdlingResource,
    private val dispatchers: DispatcherProvider
) : ViewModel() {
    private val _state = MutableStateFlow<Resource<AuthUser>>(Resource.starting())
    val state: StateFlow<Resource<AuthUser>> = _state

    var email: String? = null
    var password: String? = null

    val isValidEmail = ObservableBoolean(false)
    val isValidPassWord = ObservableBoolean(false)
    val isLoading = ObservableBoolean(false)


    fun doLogin(
        _email: String,
        _password: String
    ) = viewModelScope.launch(dispatchers.default) {
        idlingResource.wrapper {
            isLoading.on {
                _state.emit(loginWithEmailAndPassword(_email, _password))
            }
        }
    }
}

