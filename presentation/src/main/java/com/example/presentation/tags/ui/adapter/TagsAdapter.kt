package com.example.presentation.tags.ui.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.presentation.tags.model.TagUiModel

class TagsAdapter(
    private val onItemClick: (TagUiModel) -> Unit,
) : PagingDataAdapter<TagUiModel, TagViewHolder>(COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        return TagViewHolder.create(
            parent
        )
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        getItem(position)?.let {tagModel->
            holder.onBind(tagModel)
            holder.itemView.setOnClickListener { onItemClick.invoke(tagModel) }
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<TagUiModel>() {
            override fun areItemsTheSame(oldItem: TagUiModel, newItem: TagUiModel): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TagUiModel, newItem: TagUiModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}