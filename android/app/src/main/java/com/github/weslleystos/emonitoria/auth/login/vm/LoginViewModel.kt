package com.github.weslleystos.emonitoria.auth.login.vm

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.usecase.LoginWithEmailAndPasswordUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.EspressoIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
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

    fun doLogin(_email: String, _password: String) = viewModelScope.launch(Dispatchers.IO) {
        EspressoIdlingResource.increment()
        _state.run {
            emit(Resource.loading())
            isLoading.set(true)
            emit(loginWithEmailAndPassword(_email, _password))
            isLoading.set(false)
        }
        EspressoIdlingResource.decrement()
    }
}