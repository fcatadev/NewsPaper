package com.example.newspaper.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.databinding.FragmentHomeBinding
import com.example.newspaper.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()

    override fun onInitDataBinding() {
        observeEvent(viewModel.homeViewEvent, ::onViewEvent)
        binding.viewModel = viewModel
    }

    private fun onViewEvent(viewEvent: HomeViewEvent) {
        when (viewEvent) {
            is HomeViewEvent.NavigateToLogin -> {
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
    }

}