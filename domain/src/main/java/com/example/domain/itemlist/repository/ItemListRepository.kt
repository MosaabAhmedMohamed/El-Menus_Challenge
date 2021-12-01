package com.example.domain.itemlist.repository

import com.example.domain.itemlist.entity.model.ItemModel
import com.example.domain.itemlist.entity.prams.ItemListPrams
import io.reactivex.Completable
import io.reactivex.Flowable

interface ItemListRepository {

    fun getItems(itemListPrams: ItemListPrams): Flowable<List<ItemModel>>

    fun reFetchItemsFromRemote(tagName: ItemListPrams): Completable

}