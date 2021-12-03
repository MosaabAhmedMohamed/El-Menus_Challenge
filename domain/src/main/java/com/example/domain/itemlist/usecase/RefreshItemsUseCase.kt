package com.example.domain.itemlist.usecase

import com.example.domain.itemlist.entity.prams.ItemListPrams
import com.example.domain.itemlist.repository.ItemListRepository
import io.reactivex.Completable
import javax.inject.Inject

class RefreshItemsUseCase @Inject constructor(private val itemListRepository: ItemListRepository) {

    fun refreshItems(tagName: String,tagId: String): Completable {
        return itemListRepository.reFetchItemsFromRemote(ItemListPrams(tagName,tagId))
    }

}