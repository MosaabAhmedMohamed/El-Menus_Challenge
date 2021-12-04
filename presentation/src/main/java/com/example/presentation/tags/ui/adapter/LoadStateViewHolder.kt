package com.example.presentation.tags.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import com.example.presentation.R
import com.example.presentation.base.ui.BaseViewHolder
import com.example.presentation.databinding.ItemLoadStateBinding

class LoadStateViewHolder(
    private val binding: ItemLoadStateBinding,
    private val retryCallback: () -> Unit
) : BaseViewHolder<LoadState>(binding.root) {


    override fun onBind(item: LoadState?) {
        binding.loadingView.handleVisibility(item is LoadState.Loading)
        binding.errorView.handleVisibility(item is LoadState.Error)
        binding.errorView.errorMessage((item as? LoadState.Error)?.error?.message?:"")

        binding.errorView.setOnClickListener { retryCallback() }
    }

    companion object{
        fun create(
            parent: ViewGroup,
            retryCallback: () -> Unit
        ): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_load_state, parent, false)

            val binding = ItemLoadStateBinding.bind(view)

            return LoadStateViewHolder(binding, retryCallback)
        }
    }
}