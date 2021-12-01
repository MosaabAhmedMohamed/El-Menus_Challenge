package com.example.presentation.itemlist.ui.adapter

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.itemlist.entity.model.ItemModel

class ItemsAdapter(
    private val itemClickAction: (item: ItemModel) -> Unit) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private var products: MutableList<ItemModel?> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = products[position]
        item?.let {
            handleBinding(holder, item)
            holder.itemView.setOnClickListener { itemClickAction.invoke(item) }
        }
    }

    private fun handleBinding(
        holder: ItemViewHolder,
        item: ItemModel?
    ) {
        holder.onBind(item)
    }


    override fun getItemCount() = products.size ?: 0

    @SuppressLint("NotifyDataSetChanged")
    fun setItems(result: MutableList<ItemModel?>) {
        products = result
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun clear() {
        products.clear()
        notifyDataSetChanged()
    }
}