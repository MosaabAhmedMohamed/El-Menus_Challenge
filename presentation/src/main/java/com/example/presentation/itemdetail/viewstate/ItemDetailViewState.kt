package com.example.presentation.itemdetail.viewstate

import com.example.presentation.itemlist.model.ItemUiModel

sealed class ItemDetailViewState {
    data class onSuccess(val result: ItemUiModel) : ItemDetailViewState()
}