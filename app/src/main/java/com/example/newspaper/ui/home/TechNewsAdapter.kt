package com.example.newspaper.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.core.BaseAdapter
import com.example.newspaper.data.model.response.ArticleResponse

class TechNewsAdapter : BaseAdapter<ArticleResponse>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): RecyclerView.ViewHolder {
        return TechNewsViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TechNewsViewHolder) {
            holder.bind(getItem(position))
        }
    }

}