package com.example.domain.itemlist.usecase

import com.example.domain.itemlist.repository.ItemListRepository
import io.reactivex.Completable
import javax.inject.Inject

class RefreshItemsUseCase @Inject constructor(private val itemListRepository: ItemListRepository) {

    fun refreshItems(): Completable {
        return itemListRepository.reFetchItemsFromRemote()
    }

}