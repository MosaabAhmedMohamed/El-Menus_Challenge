package com.example.data.factory

import com.example.core.test.util.DataFactory
import com.example.data.itemlist.source.local.model.ItemLocalModel
import com.example.data.itemlist.source.remote.model.ItemRemoteModel
import com.example.domain.itemlist.entity.prams.ItemListPrams

object ItemListFactory {

    const val tagId = "12"

    val itemListPrams = ItemListPrams("test", tagId)

    fun generateDummyItemListRemoteModels(size: Int): List<ItemRemoteModel> {
        val productList = mutableListOf<ItemRemoteModel>()
        repeat(size) {
            productList.add(generateItemListRemoteModel())
        }
        return productList
    }

    fun generateItemListRemoteModel(): ItemRemoteModel {
        return ItemRemoteModel(
            id = DataFactory.getRandomLong(),
            name = DataFactory.getRandomString(),
            photoUrl = DataFactory.getRandomString(),
            description = DataFactory.getRandomString()
        )
    }


    fun generateDummyItemListLocalModels(size: Int): List<ItemLocalModel> {
        val productList = mutableListOf<ItemLocalModel>()
        repeat(size) {
            productList.add(generateItemListLocalModel())
        }
        return productList
    }

    fun generateItemListLocalModel(): ItemLocalModel {
        return ItemLocalModel(
            id = DataFactory.getRandomString(),
            name = DataFactory.getRandomString(),
            photoUrl = DataFactory.getRandomString(),
            description = DataFactory.getRandomString(),
            tagId = tagId
        )
    }


}