package com.example.presentation.itemlist.viewstate

import com.example.presentation.itemlist.model.ItemUiModel

sealed class ItemListViewState {
    object Loading : ItemListViewState()
    object onEmptyState : ItemListViewState()
    data class onSuccess(val result: List<ItemUiModel>) : ItemListViewState()
    data class onError(val error: Throwable) : ItemListViewState()
}