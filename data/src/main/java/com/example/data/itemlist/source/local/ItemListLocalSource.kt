package com.example.data.itemlist.source.local

import com.example.data.itemlist.source.local.dao.ItemListDao
import com.example.data.itemlist.source.local.model.ItemLocalModel
import io.reactivex.Completable
import io.reactivex.Flowable
import javax.inject.Inject

class ItemListLocalSource @Inject constructor(private val itemListDao: ItemListDao) {

    fun getItemList(tagId: String): Flowable<List<ItemLocalModel>> {
        return itemListDao.getItemList(tagId)
    }

    fun cacheItemList(items: List<ItemLocalModel>, tagId: String): Completable {
        return Completable.defer {
            itemListDao.deleteAllEntriesOfTag(tagId).subscribe()
            itemListDao.insertItemList(items).subscribe()
            Completable.complete()
        }
    }

}