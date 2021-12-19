package com.github.weslleystos.emonitoria.auth.login.ui

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
import com.github.weslleystos.emonitoria.R
import com.github.weslleystos.emonitoria.auth.login.vm.LoginViewModel
import com.github.weslleystos.emonitoria.databinding.FragmentLoginBinding
import com.github.weslleystos.emonitoria.domain.shared.exceptions.AuthException
import com.github.weslleystos.emonitoria.domain.shared.model.onFailure
import com.github.weslleystos.emonitoria.domain.shared.model.onStarting
import com.github.weslleystos.emonitoria.domain.shared.model.onSuccess
import com.github.weslleystos.emonitoria.shared.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        _binding?.lifecycleOwner = viewLifecycleOwner
        _binding!!.viewModel = loginViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = binding.run {
        inputEmail.doAfterTextChanged { email ->
            loginViewModel.isValidEmail.set(validateEmail(email.toString()))
        }

        inputPassword.doAfterTextChanged { password ->
            loginViewModel.isValidPassWord.set(validatePassword(password.toString()))
        }

        btnRecover.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toRecoveryFragment())
        }

        register.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.toRegisterFragment())
        }

        lifecycleScope.launch {
            repeatOnLifecycle(STARTED) {
                loginViewModel.state.collect { result ->
                    result.run {
                        onStarting {
                            hideKeyboard()
                        }

                        onSuccess {
                            // Todo("redirect to home screen")
                            snackBar(R.string.success)
                        }
                        onFailure { exception ->
                            when (exception) {
                                is AuthException -> snackBar(R.string.invalid_login)
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

    private fun validatePassword(password: String): Boolean = binding.run {
        return@run ValidatorString(password)
            .isNotEmpty { edtPassword.error = getString(R.string.required_field) }
            .isGreatThan(7) { edtPassword.error = getString(R.string.password_less_then, 8) }
            .isLessThan(15) { edtPassword.error = getString(R.string.password_more_then, 15) }
            .isValid { binding.edtPassword.error = null }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}