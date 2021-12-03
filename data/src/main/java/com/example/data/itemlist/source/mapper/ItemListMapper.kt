package com.example.data.itemlist.source.mapper

import com.example.data.itemlist.source.local.model.ItemLocalModel
import com.example.data.itemlist.source.remote.model.ItemRemoteModel
import com.example.domain.itemlist.entity.model.ItemModel

fun List<ItemRemoteModel>.mapToLocalModels(tagId: String): List<ItemLocalModel> {
    return this.map { it.mapToLocalModel(tagId) }
}

fun ItemRemoteModel.mapToLocalModel(tagId: String): ItemLocalModel {
    return ItemLocalModel(
        this.id.toString().plus(tagId),
        this.name,
        this.photoUrl,
        this.description,
        tagId
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
