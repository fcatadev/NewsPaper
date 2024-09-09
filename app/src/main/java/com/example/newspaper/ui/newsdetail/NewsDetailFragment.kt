package com.example.newspaper.ui.newsdetail

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.newspaper.R
import com.example.newspaper.core.BaseFragment
import com.example.newspaper.data.model.favorite.FavoriteArticle
import com.example.newspaper.data.remote.NewsKeys
import com.example.newspaper.databinding.FragmentNewsDetailBinding
import com.example.newspaper.extensions.observeEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsDetailFragment :
    BaseFragment<NewsDetailViewModel, FragmentNewsDetailBinding>(R.layout.fragment_news_detail) {

    override val viewModel: NewsDetailViewModel by viewModels()

    private var currentArticle: FavoriteArticle? = null

    override fun onInitDataBinding() {
        binding.viewModel = viewModel

        getNewsDetailData()

        viewModel.favoriteArticles.observe(viewLifecycleOwner) { articles ->
            currentArticle?.let { article ->
                val isFavorite = articles.any { it.url == article.url }
                viewModel.checkIfFavorite(article)
                updateFavoriteIcon(isFavorite)
            }
        }

        binding.ibFavorite.setOnClickListener {
            currentArticle?.let { article ->
                viewModel.toggleFavorite(article, requireContext(), getString(R.string.already_added_fav))
            }
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

            currentArticle = getArticleFromUI()
            currentArticle?.let { viewModel.checkIfFavorite(it) }
        }
    }

    private fun getArticleFromUI(): FavoriteArticle {
        return FavoriteArticle(
            title = binding.tvHeader.text.toString(),
            author = binding.tvAuthor.text.toString(),
            description = binding.tvDescription.text.toString(),
            url = arguments?.getString(NewsKeys.URL).orEmpty(),
            urlToImage = arguments?.getString(NewsKeys.URLTOIMAGE),
            publishedAt = binding.tvDate.text.toString()
        )
    }

    private fun updateFavoriteIcon(isFavorite: Boolean) {
        binding.ibFavorite.setImageResource(
            if (isFavorite) R.drawable.ic_favorite else R.drawable.ic_favorite_border
        )
    }
}