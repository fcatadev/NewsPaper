package com.example.newspaper.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.core.BaseAdapter
import com.example.newspaper.data.model.response.ArticleResponse

class TopHeadlinesAdapter(
    private val onItemClick: (ArticleResponse) -> Unit
) : BaseAdapter<ArticleResponse>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): RecyclerView.ViewHolder {
        return TopHeadlinesViewHolder(parent, inflater, onItemClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TopHeadlinesViewHolder) {
            holder.bind(getItem(position))
        }
    }
}