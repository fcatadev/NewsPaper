package com.example.newspaper.ui.newsdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.data.remote.NewsKeys
import com.example.newspaper.databinding.FragmentNewsDetailBinding
import com.example.newspaper.extensions.observeEvent

class NewsDetailFragment :
    BaseFragment<NewsDetailViewModel, FragmentNewsDetailBinding>(R.layout.fragment_news_detail) {

    override val viewModel: NewsDetailViewModel by viewModels()

    override fun onInitDataBinding() {
        observeEvent(viewModel.newsDetailViewEvent, ::onViewEvent)
        binding.viewModel = viewModel

        getNewsDetailData()
    }

    private fun onViewEvent(event: NewsDetailViewEvent) {
        when (event) {
            //TODO
        }
    }

    private fun getNewsDetailData() {
        arguments?.let { bundle ->
            binding.tvHeader.text = bundle.getString(NewsKeys.TITLE)
            binding.tvAuthor.text = bundle.getString(NewsKeys.AUTHOR)
            binding.tvDescription.text = bundle.getString(NewsKeys.DESCRIPTION)
            binding.tvDate.text = bundle.getString(NewsKeys.PUBLISHEDAT)
            Glide.with(requireContext())
                .load(bundle.getString(NewsKeys.URLTOIMAGE))
                .into(binding.ivNewsPic)
        }
    }

}