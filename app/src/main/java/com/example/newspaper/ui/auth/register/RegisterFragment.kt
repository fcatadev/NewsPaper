package com.example.newspaper.ui.auth.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.databinding.FragmentRegisterBinding
import com.example.newspaper.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment :
    BaseFragment<RegisterViewModel, FragmentRegisterBinding>(R.layout.fragment_register) {

    override val viewModel: RegisterViewModel by viewModels()

    override fun onInitDataBinding() {
        observeEvent(viewModel.registerEvent, ::onViewEvent)
        binding.viewModel = viewModel
    }

    private fun onViewEvent(viewEvent: RegisterViewEvent) {
        when(viewEvent) {
            is RegisterViewEvent.NavigateToLogin -> {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }
        }
    }

}