package com.example.newspaper.ui.home

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.newspaper.R
import com.example.newspaper.core.BaseViewHolder
import com.example.newspaper.data.model.response.ArticleResponse
import com.example.newspaper.databinding.ItemTopHeadlinesBinding
import com.example.newspaper.extensions.executeAfter

class TopHeadlinesViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater
) : BaseViewHolder<ItemTopHeadlinesBinding>(ItemTopHeadlinesBinding.inflate(inflater, parent, false)) {

    fun bind(item: ArticleResponse) {
        binding.executeAfter {
            this.item = item

            binding.pbNewsPhoto.visibility = View.VISIBLE

            // Glide ile resim yükleme ve listener ile durumu kontrol etme
            Glide.with(binding.ivUrlToImage.context)
                .load(item.urlToImage)
                .error(R.drawable.ic_launcher_foreground)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Yükleme başarısız olursa ProgressBar'ı gizle
                        binding.pbNewsPhoto.visibility = View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>?,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {
                        // Resim yüklendiğinde ProgressBar'ı gizle
                        binding.pbNewsPhoto.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.ivUrlToImage)
        }
    }

}