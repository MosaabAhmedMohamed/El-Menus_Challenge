package com.example.presentation.itemlist.mapping


import com.example.domain.itemlist.entity.model.ItemModel
import com.example.presentation.factory.ItemListFactory
import com.example.presentation.itemlist.model.ItemUiModel
import com.example.presentation.itemlist.model.mapper.mapToUiModel
import org.junit.Assert
import org.junit.Test

class ItemListMapper {

    @Test
    fun mapToItemModel() {
        // Arrange
        val itemDomain= ItemListFactory.generateItemModel()

        // Act
        val itemUIModel = itemDomain.mapToUiModel()

        // Assert
        assertItemModelMapDataEqual(itemDomain, itemUIModel)
    }

    /**
     * Helpers Methods
     */
    private fun assertItemModelMapDataEqual(itemDomain: ItemModel, itemUIModel: ItemUiModel) {
        Assert.assertEquals(itemDomain.id, itemUIModel.id)
        Assert.assertEquals(itemDomain.name, itemUIModel.name)
        Assert.assertEquals(itemDomain.photoUrl, itemUIModel.photoUrl)
        Assert.assertEquals(itemDomain.description, itemUIModel.description)
    }


}