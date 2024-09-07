package com.example.newspaper.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newspaper.core.BaseAdapter
import com.example.newspaper.data.model.response.ArticleResponse

class TopHeadlinesAdapter : BaseAdapter<ArticleResponse>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): RecyclerView.ViewHolder {
        return TopHeadlinesViewHolder(parent, inflater)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("Adapter", "onBindViewHolder: Item position $position")
        if (holder is TopHeadlinesViewHolder) {
            holder.bind(getItem(position))
        }
    }
}