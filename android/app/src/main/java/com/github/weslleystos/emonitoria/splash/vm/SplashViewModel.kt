package com.github.weslleystos.emonitoria.splash.vm

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.weslleystos.emonitoria.domain.auth.model.AuthUser
import com.github.weslleystos.emonitoria.domain.auth.usecase.GetAuthenticateUserUseCase
import com.github.weslleystos.emonitoria.domain.shared.model.Resource
import com.github.weslleystos.emonitoria.shared.util.DispatcherProvider
import com.github.weslleystos.emonitoria.shared.util.EspressoCounterIdlingResource
import com.github.weslleystos.emonitoria.shared.util.wrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getAuthenticateUserUseCase: GetAuthenticateUserUseCase,
    private val idlingResource: EspressoCounterIdlingResource,
    private val dispatchers: DispatcherProvider
) : ViewModel(), DefaultLifecycleObserver {
    private val _state = MutableStateFlow<Resource<AuthUser>>(Resource.starting())
    val state: StateFlow<Resource<AuthUser>> = _state

    override fun onCreate(owner: LifecycleOwner) {
        getAuthenticateUser()
    }

    fun getAuthenticateUser() = viewModelScope.launch(dispatchers.default) {
        idlingResource.wrapper {
            _state.emit(getAuthenticateUserUseCase())
        }
    }
}