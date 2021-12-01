package com.example.data.itemlist.source.mapper

import com.example.data.itemlist.source.local.model.ItemLocalModel
import com.example.data.itemlist.source.remote.model.ItemRemoteModel
import com.example.domain.itemlist.entity.model.ItemModel

fun List<ItemRemoteModel>.mapToLocalModels(): List<ItemLocalModel> {
    return this.map { it.mapToLocalModel() }
}

fun ItemRemoteModel.mapToLocalModel(): ItemLocalModel {
    return ItemLocalModel(
        this.id.toString(),
        this.name,
        this.photoUrl,
        this.description
    )
}


fun List<ItemLocalModel>.mapToItemModel(): List<ItemModel> {
    return this.map { it.mapToItemModel() }
}

fun ItemLocalModel.mapToItemModel(): ItemModel {
    return ItemModel(
        this.id,
        this.name,
        this.photoUrl,
        this.description
    )
}
