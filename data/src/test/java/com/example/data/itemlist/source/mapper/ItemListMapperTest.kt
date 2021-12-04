package com.example.data.itemlist.source.mapper

import com.example.data.factory.ItemListFactory
import com.example.data.factory.ItemListFactory.tagId
import com.example.data.itemlist.source.local.model.ItemLocalModel
import com.example.data.itemlist.source.remote.model.ItemRemoteModel
import com.example.domain.itemlist.entity.model.ItemModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ItemListMapperTest {


    @Test
    fun mapToLocal() {
        // Arrange
        val itemRemote = ItemListFactory.generateItemListRemoteModel()

        // Act
        val itemLocalModel = itemRemote.mapToLocalModel(tagId)

        // Assert
        assertItemMapDataEqual(itemLocalModel, itemRemote)
    }

    /**
     * Helpers Methods
     */
    private fun assertItemMapDataEqual(itemLocalModel: ItemLocalModel, itemRemote: ItemRemoteModel) {
        assertEquals(itemLocalModel.id, itemRemote.id.toString().plus(tagId))
        assertEquals(itemLocalModel.name, itemRemote.name)
        assertEquals(itemLocalModel.photoUrl, itemRemote.photoUrl)
        assertEquals(itemLocalModel.description, itemRemote.description)
    }


    @Test
    fun mapToItemModel() {
        // Arrange
        val itemLocal= ItemListFactory.generateItemListLocalModel()

        // Act
        val itemModel = itemLocal.mapToItemModel()

        // Assert
        assertItemModelMapDataEqual(itemLocal, itemModel)
    }

    /**
     * Helpers Methods
     */
    private fun assertItemModelMapDataEqual(itemLocalModel: ItemLocalModel, itemRemote: ItemModel) {
        assertEquals(itemLocalModel.id, itemRemote.id)
        assertEquals(itemLocalModel.name, itemRemote.name)
        assertEquals(itemLocalModel.photoUrl, itemRemote.photoUrl)
        assertEquals(itemLocalModel.description, itemRemote.description)
    }

}