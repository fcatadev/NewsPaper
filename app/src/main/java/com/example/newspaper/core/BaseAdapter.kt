package com.example.newspaper.core

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T : Any>(
    itemsSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem -> oldItem == newItem },
    contentsSame: (oldItem: T, newItem: T) -> Boolean = { oldItem, newItem -> oldItem == newItem }
) : ListAdapter<T, RecyclerView.ViewHolder>(object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(old: T, new: T): Boolean = itemsSame(old, new)
    override fun areContentsTheSame(old: T, new: T): Boolean = contentsSame(old, new)
}) {

    abstract fun onCreateViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): RecyclerView.ViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        onCreateViewHolder(
            parent = parent,
            inflater = LayoutInflater.from(parent.context)
        )
}

