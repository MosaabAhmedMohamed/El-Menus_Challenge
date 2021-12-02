package com.example.presentation.itemlist.model.mapper

import com.example.domain.itemlist.entity.model.ItemModel
import com.example.presentation.itemlist.model.ItemUiModel

fun List<ItemModel>.mapToUiModels(): List<ItemUiModel> {
    return this.map { it.mapToUiModel() }
}

fun ItemModel.mapToUiModel(): ItemUiModel {
    return ItemUiModel(
        this.id,
        this.name,
        this.photoUrl,
        this.description
    )
}