package com.example.presentation.itemlist.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.itemlist.model.ItemUiModel

class ItemsAdapter(
    private val itemClickAction: (item: ItemUiModel, posterImg: ImageView) -> Unit
) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private var products: MutableList<ItemUiModel?> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = products[position]
        item?.let {
            handleBinding(holder, item)
            setOnItemClicked(holder, item)
        }
    }

    private fun setOnItemClicked(
        holder: ItemViewHolder,
        item: ItemUiModel
    ) {
        holder.itemView.setOnClickListener {
            itemClickAction.invoke(
                item, holder.itemView.findViewById(
                    R.id.posterIv
                )
            )
        }
    }

    private fun handleBinding(
        holder: ItemViewHolder,
        item: ItemUiModel?
    ) {
        holder.onBind(item)
    }


    override fun getItemCount() = products.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(result: MutableList<ItemUiModel?>) {
        products = result
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        products.clear()
        notifyDataSetChanged()
    }
}