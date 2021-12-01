package com.example.presentation.itemlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.base.ui.BaseViewHolder
import com.example.presentation.databinding.ItemTagBinding
import com.example.presentation.itemlist.model.ItemUiModel

class ItemViewHolder(private val binding: ItemTagBinding) : BaseViewHolder<ItemUiModel>(binding.root) {

    override fun onBind(item: ItemUiModel?) {
        item?.let {
            binding.tagTitleTv.text = it.name
            Glide.with(binding.root)
                .load(it.photoUrl)
                .into(binding.poster)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tag, parent, false)

            val binding = ItemTagBinding.bind(view)

            return ItemViewHolder(
                binding
            )
        }
    }
}