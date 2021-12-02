package com.example.presentation.itemlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.presentation.R
import com.example.presentation.base.ui.BaseViewHolder
import com.example.presentation.databinding.ItemFoodBinding
import com.example.presentation.itemlist.model.ItemUiModel

class ItemViewHolder(private val binding: ItemFoodBinding) : BaseViewHolder<ItemUiModel>(binding.root) {

    override fun onBind(item: ItemUiModel?) {
        item?.let {
            binding.titleTv.text = it.name
            Glide.with(binding.root)
                .load(it.photoUrl)
                .into(binding.posterIv)
        }
    }

    companion object {
        fun create(parent: ViewGroup): ItemViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_food, parent, false)

            val binding = ItemFoodBinding.bind(view)

            return ItemViewHolder(
                binding
            )
        }
    }
}