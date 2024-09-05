package com.example.newspaper.ui.auth.login

import androidx.fragment.app.viewModels
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>(R.layout.fragment_login) {

    override val viewModel: LoginViewModel by viewModels()

    override fun onInitDataBinding() {
        TODO("Not yet implemented")
    }


}