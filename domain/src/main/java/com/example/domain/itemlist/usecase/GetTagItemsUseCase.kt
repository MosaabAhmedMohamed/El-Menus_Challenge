package com.example.domain.itemlist.usecase

import com.example.domain.itemlist.entity.model.ItemModel
import com.example.domain.itemlist.entity.prams.ItemListPrams
import com.example.domain.itemlist.repository.ItemListRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetTagItemsUseCase @Inject constructor(private val itemListRepository: ItemListRepository) {

    fun getItems(tagName:String): Flowable<List<ItemModel>> {
        return itemListRepository.getItems(ItemListPrams(tagName))
    }

}