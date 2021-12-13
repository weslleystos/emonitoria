package com.github.weslleystos.emonitoria.splash.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.github.weslleystos.emonitoria.App
import com.github.weslleystos.emonitoria.databinding.FragmentSplashBinding
import com.github.weslleystos.emonitoria.domain.shared.model.onFailure
import com.github.weslleystos.emonitoria.domain.shared.model.onSuccess
import com.github.weslleystos.emonitoria.shared.ui.BindingFragment
import com.github.weslleystos.emonitoria.splash.vm.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BindingFragment<FragmentSplashBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentSplashBinding::inflate

    private val splashViewModel by viewModels<SplashViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(splashViewModel)

        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                splashViewModel.state.collect { result ->
                    delay(2000)
                    result.run {
                        onSuccess { data ->
                            App.authUser = data
                            findNavController().navigate(SplashFragmentDirections.toHomeFragment())
                        }
                        onFailure {
                            findNavController().navigate(SplashFragmentDirections.toSignInFragment())
                        }
                    }
                }
            }
        }
    }
}