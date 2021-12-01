package com.example.data.itemlist.source.local

import com.example.data.itemlist.source.local.dao.ItemListDao
import com.example.data.itemlist.source.local.model.ItemLocalModel
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ItemListLocalSource @Inject constructor(private val itemListDao: ItemListDao) {

    fun getItemList(): Flowable<List<ItemLocalModel>> {
        return itemListDao.getItemList()
    }

    fun cacheItemList(items: List<ItemLocalModel>): Completable {
        return Completable.defer {
            itemListDao.deleteAllEntries()
            itemListDao.insertItemList(items).subscribe()
            Completable.complete()
        }
    }

}