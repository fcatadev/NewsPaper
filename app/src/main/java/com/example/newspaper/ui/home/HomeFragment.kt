package com.example.newspaper.ui.home

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.data.remote.ApiParameters
import com.example.newspaper.data.remote.NewsKeys
import com.example.newspaper.databinding.FragmentHomeBinding
import com.example.newspaper.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(R.layout.fragment_home) {

    override val viewModel: HomeViewModel by viewModels()
    private val topHeadlinesAdapter = TopHeadlinesAdapter { article ->
        viewModel.onArticleClicked(article)
    }
    private val techNewsAdapter = TechNewsAdapter { article ->
        viewModel.onArticleClicked(article)
    }

    override fun onInitDataBinding() {
        observeEvent(viewModel.homeViewEvent, ::onViewEvent)
        binding.viewModel = viewModel

        binding.rvTopHeadlines.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvTopHeadlines.adapter = topHeadlinesAdapter
        binding.rvTechNews.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.rvTechNews.adapter = techNewsAdapter

        viewModel.articles.observe(viewLifecycleOwner) { articles ->
            topHeadlinesAdapter.submitList(articles)
        }

        viewModel.allTechNews.observe(viewLifecycleOwner) { allTechNews ->
            techNewsAdapter.submitList(allTechNews)
        }

        viewModel.getTopHeadlines(
            ApiParameters.COUNTRY,
            ApiParameters.API_KEY,
            requireContext(),
            getString(R.string.connection_fail)
        )
        viewModel.getAllTechNews(
            ApiParameters.QUERY,
            ApiParameters.API_KEY,
            requireContext(),
            getString(R.string.connection_fail)
        )
    }

    private fun onViewEvent(viewEvent: HomeViewEvent) {
        when (viewEvent) {
            is HomeViewEvent.NavigateToLogin -> {
                findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
            }

            is HomeViewEvent.NavigateToNewsDetail -> {
                val bundle = Bundle().apply {
                    putString(NewsKeys.AUTHOR, viewEvent.author)
                    putString(NewsKeys.TITLE, viewEvent.title)
                    putString(NewsKeys.DESCRIPTION, viewEvent.description)
                    putString(NewsKeys.URL, viewEvent.url)
                    putString(NewsKeys.URLTOIMAGE, viewEvent.urlToImage)
                    putString(NewsKeys.PUBLISHEDAT, viewEvent.publishedAt)
                    putString(NewsKeys.CONTENT, viewEvent.content)
                }

                findNavController().navigate(R.id.action_homeFragment_to_newsDetailFragment, bundle)
            }
        }
    }

}