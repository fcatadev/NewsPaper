package com.example.newspaper.ui.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment :
    BaseFragment<FavoriteViewModel, FragmentFavoriteBinding>(R.layout.fragment_favorite) {

    override val viewModel: FavoriteViewModel by viewModels()
    private val favoriteAdapter = FavoriteAdapter { article ->
        viewModel.removeFavorite(article)
    }

    override fun onInitDataBinding() {
        binding.viewModel = viewModel

        binding.rvFavoriteNews.layoutManager =
            GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
        binding.rvFavoriteNews.adapter = favoriteAdapter
        viewModel.favoriteArticles.observe(viewLifecycleOwner) { articles ->
            favoriteAdapter.submitList(articles)
        }
    }
}