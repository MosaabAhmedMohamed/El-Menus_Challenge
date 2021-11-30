package com.example.presentation.tags.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.domain.tags.model.TagModel

class TagsAdapter : PagingDataAdapter<TagModel, TagViewHolder>(
    COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TagModel>() {
            override fun areItemsTheSame(oldItem: TagModel, newItem:TagModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TagModel, newItem: TagModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}