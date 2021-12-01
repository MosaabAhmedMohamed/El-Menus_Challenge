package com.example.presentation.itemlist.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.domain.itemlist.entity.model.ItemModel
import com.example.presentation.R
import com.example.presentation.base.ui.BaseViewHolder
import com.example.presentation.databinding.ItemTagBinding

class ItemViewHolder(private val binding: ItemTagBinding) : BaseViewHolder<ItemModel>(binding.root) {

    override fun onBind(item: ItemModel?) {
        item?.let {
            binding.tagTitleTv.text = it.name
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