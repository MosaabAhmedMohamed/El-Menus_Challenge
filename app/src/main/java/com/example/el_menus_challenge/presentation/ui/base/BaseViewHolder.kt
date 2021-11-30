package com.example.el_menus_challenge.presentation.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<I>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {
    abstract fun onBind(item: I?)
}