package com.example.presentation.itemlist.viewstate

import com.example.domain.itemlist.entity.model.ItemModel

sealed class ItemListViewState {
    object Loading : ItemListViewState()
    object onEmptyState : ItemListViewState()
    data class onSuccess(val result: List<ItemModel>) : ItemListViewState()
    data class onError(val error: Throwable) : ItemListViewState()
}