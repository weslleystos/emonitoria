package com.github.weslleystos.emonitoria.auth.register.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.github.weslleystos.emonitoria.App
import com.github.weslleystos.emonitoria.R
import com.github.weslleystos.emonitoria.auth.register.vm.RegisterViewModel
import com.github.weslleystos.emonitoria.databinding.FragmentRegisterBinding
import com.github.weslleystos.emonitoria.domain.shared.model.onFailure
import com.github.weslleystos.emonitoria.domain.shared.model.onSuccess
import com.github.weslleystos.emonitoria.shared.util.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val registerViewModel by viewModels<RegisterViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = registerViewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?): Unit = binding.run {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        toolbar.setupWithNavController(navController, appBarConfiguration)

        alreadyAccount.setOnClickListener {
            navController.popBackStack()
        }

        inputName.doAfterTextChanged { name ->
            registerViewModel.isValidConfirmPassWord.set(validateName(name.toString()))
        }

        inputEmail.doAfterTextChanged { email ->
            registerViewModel.isValidEmail.set(validateEmail(email.toString()))
        }

        inputPassword.doAfterTextChanged { password ->
            registerViewModel.isValidPassWord.set(validatePassword(password.toString()))
            registerViewModel.isValidConfirmPassWord.set(
                validatePasswordConfirm(
                    password.toString(),
                    registerViewModel.confirmPassword
                )
            )
        }

        inputConfirmPassword.doAfterTextChanged { confirmPassword ->
            registerViewModel.isValidConfirmPassWord.set(
                validatePasswordConfirm(
                    registerViewModel.password,
                    confirmPassword.toString()
                )
            )
        }

        collectLatestLifecycleFlow(registerViewModel.state) { result ->
            result.run {
                onSuccess {
                    App.authUser = it
                    snackBar(R.string.success) {
                        onDismissed {
                            navController.navigate(RegisterFragmentDirections.toHomeFragment())
                        }
                    }
                }
                onFailure {
                    snackBar(R.string.registered_user)
                }
            }
        }
    }

    private fun validateName(name: String): Boolean = binding.run {
        return@run ValidatorString(name)
            .isNotEmpty { edtName.error = getString(R.string.required_field) }
            .isGreatThan(2) { edtName.error = getString(R.string.name_less_than, 3) }
            .isValid { binding.edtName.error = null }
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

    private fun validatePasswordConfirm(
        password: String?,
        confirmPassword: String?
    ): Boolean = binding.run {
        return@run if (password == confirmPassword) {
            binding.edtConfirmPassword.error = null
            true
        } else {
            binding.edtConfirmPassword.error = getString(R.string.passwords_different)
            false
        }
    }
}