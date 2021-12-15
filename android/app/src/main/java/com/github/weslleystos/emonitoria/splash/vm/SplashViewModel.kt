package com.github.weslleystos.emonitoria.splash.vm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.usecase.GetAuthenticateUserUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.wrapperIdlingResource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAuthenticateUserUseCase: GetAuthenticateUserUseCase
) : ViewModel(), DefaultLifecycleObserver {
    private val _state = MutableStateFlow<Resource<AuthUser>>(Resource.loading())
    val state: StateFlow<Resource<AuthUser>> = _state

    override fun onCreate(owner: LifecycleOwner) {
        wrapperGetAuthenticateUser()
    }

    private fun wrapperGetAuthenticateUser() = wrapperIdlingResource(viewModelScope) {
        getAuthenticateUser()
    }

    suspend fun getAuthenticateUser() {
        _state.emit(getAuthenticateUserUseCase())
    }
}