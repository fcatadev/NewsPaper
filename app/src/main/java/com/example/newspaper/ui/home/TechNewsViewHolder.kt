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
import com.example.newspaper.databinding.ItemTechNewsBinding
import com.example.newspaper.extensions.executeAfter

class TechNewsViewHolder(
    parent: ViewGroup,
    inflater: LayoutInflater,
    private val onItemClick: (ArticleResponse) -> Unit
) : BaseViewHolder<ItemTechNewsBinding>(ItemTechNewsBinding.inflate(inflater, parent,false)) {

    fun bind(item: ArticleResponse) {
        binding.executeAfter {
            this.item = item

            binding.pbNewsPhoto.visibility = View.VISIBLE

            Glide.with(binding.ivUrlToImage.context)
                .load(item.urlToImage)
                .error(R.drawable.bg_image_not_found)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
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
                        binding.pbNewsPhoto.visibility = View.GONE
                        return false
                    }
                })
                .into(binding.ivUrlToImage)

            binding.root.setOnClickListener {
                onItemClick(item)
            }
        }
    }

}