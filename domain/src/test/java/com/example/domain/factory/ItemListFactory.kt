package com.example.domain.factory

import com.example.core.test.util.DataFactory
import com.example.domain.itemlist.entity.model.ItemModel
import com.example.domain.itemlist.entity.prams.ItemListPrams

object ItemListFactory {

    const val tagId = "12"

    val itemListPrams = ItemListPrams("test", tagId)


    fun generateDummyItemModels(size: Int): List<ItemModel> {
        val productList = mutableListOf<ItemModel>()
        repeat(size) {
            productList.add(generateItemModel())
        }
        return productList
    }

    fun generateItemModel(): ItemModel {
        return ItemModel(
            id = DataFactory.getRandomString(),
            name = DataFactory.getRandomString(),
            photoUrl = DataFactory.getRandomString(),
            description = DataFactory.getRandomString()
        )
    }


}