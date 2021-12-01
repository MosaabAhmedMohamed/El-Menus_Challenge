package com.example.presentation.tags.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.domain.tags.model.TagModel
import com.example.presentation.R
import com.example.presentation.base.ui.BaseViewHolder
import com.example.presentation.databinding.ItemTagBinding

class TagViewHolder(private val binding: ItemTagBinding) : BaseViewHolder<TagModel>(binding.root) {

    override fun onBind(item: TagModel?) {
        item?.let {
            binding.tagTitleTv.text = it.name

            Glide.with(binding.root)
                .load(it.photoURL)
                .into(binding.poster)
        }
    }

    companion object {
        fun create(parent: ViewGroup): TagViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tag, parent, false)

            val binding = ItemTagBinding.bind(view)

            return TagViewHolder(
                binding
            )
        }
    }
}