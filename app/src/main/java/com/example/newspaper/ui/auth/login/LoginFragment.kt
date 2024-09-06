package com.example.newspaper.ui.auth.login

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.databinding.FragmentLoginBinding
import com.example.newspaper.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModels()

    override fun onInitDataBinding() {
        observeEvent(viewModel.loginEvent, ::onViewEvent)
        binding.viewModel = viewModel
    }

    private fun onViewEvent(viewEvent: LoginViewEvent) {
        when (viewEvent) {
            is LoginViewEvent.NavigateToRegister -> {
                findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

}