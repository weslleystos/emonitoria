package com.github.weslleystos.emonitoria.auth.recovery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.weslleystos.emonitoria.R
import com.github.weslleystos.emonitoria.auth.recovery.vm.RecoveryViewModel
import com.github.weslleystos.emonitoria.databinding.FragmentRecoveryBinding
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthInvalidUser
import com.github.weslleystos.emonitoria.domain.shared.model.onFailure
import com.github.weslleystos.emonitoria.domain.shared.model.onSuccess
import com.github.weslleystos.emonitoria.shared.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RecoveryFragment : Fragment() {
    private var _binding: FragmentRecoveryBinding? = null
    private val binding get() = _binding!!

    private val recoveryViewModel by viewModels<RecoveryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecoveryBinding.inflate(inflater, container, false)
        _binding?.lifecycleOwner = viewLifecycleOwner
        _binding?.viewModel = recoveryViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = binding.run {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)

        toolbar.setupWithNavController(navController, appBarConfiguration)

        inputEmail.doAfterTextChanged { email ->
            recoveryViewModel.isValidEmail.set(validateEmail(email.toString()))
        }

        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                recoveryViewModel.state.collect { result ->
                    result.run {
                        onSuccess {
                            snackBar(R.string.email_sent) {
                                onDismissed {
                                    navController.popBackStack()
                                }
                            }
                        }
                        onFailure { exception ->
                            when (exception) {
                                is AuthInvalidUser -> snackBar(R.string.invalid_user)
                                else -> snackBar(R.string.something_wrong)
                            }
                        }
                    }
                }
            }
        }
    }

    private fun validateEmail(email: String): Boolean = binding.run {
        return@run ValidatorString(email)
            .isNotEmpty { edtEmail.error = getString(R.string.required_field) }
            .isEmail { edtEmail.error = (getString(R.string.invalid_email)) }
            .isValid { binding.edtEmail.error = null }
    }
}