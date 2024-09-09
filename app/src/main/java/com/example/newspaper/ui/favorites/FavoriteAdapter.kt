package com.example.newspaper.ui.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.core.BaseAdapter
import com.example.newspaper.data.model.favorite.FavoriteArticle

class FavoriteAdapter(
    private val onArticleClick: (FavoriteArticle) -> Unit
) : BaseAdapter<FavoriteArticle>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): RecyclerView.ViewHolder {
        return FavoriteViewHolder(parent, inflater, onArticleClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is FavoriteViewHolder) {
            holder.bind(getItem(position))
        }
    }

}