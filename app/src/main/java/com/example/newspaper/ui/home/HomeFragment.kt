package com.example.newspaper.ui.home

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.databinding.FragmentHomeBinding
import com.example.newspaper.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()
    private val topHeadlinesAdapter = TopHeadlinesAdapter()

    override fun onInitDataBinding() {
        observeEvent(viewModel.homeViewEvent, ::onViewEvent)
        binding.viewModel = viewModel

        binding.rvTopHeadlines.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopHeadlines.adapter = topHeadlinesAdapter

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            topHeadlinesAdapter.submitList(articles)
        }


        viewModel.getTopHeadlines("us", "ffd3b86bbf304e6eb4537a796969cbb4")
    }

    private fun onViewEvent(viewEvent: HomeViewEvent) {
        when (viewEvent) {
            is HomeViewEvent.NavigateToLogin -> {
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }
        }
    }

}