package com.example.data.itemlist.repository

import com.example.data.itemlist.source.local.ItemListLocalSource
import com.example.data.itemlist.source.local.model.ItemLocalModel
import com.example.data.itemlist.source.mapper.mapToItemModel
import com.example.data.itemlist.source.mapper.mapToLocalModels
import com.example.data.itemlist.source.remote.ItemListRemoteSource
import com.example.domain.itemlist.entity.model.ItemModel
import com.example.domain.itemlist.entity.prams.ItemListPrams
import com.example.domain.itemlist.repository.ItemListRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

class ItemListRepositoryImpl @Inject constructor(
    private val itemListRemoteSource: ItemListRemoteSource,
    private val itemListLocalSource: ItemListLocalSource
) : ItemListRepository {


    override fun getItems(itemListPrams: ItemListPrams): Flowable<List<ItemModel>> {
        return itemListLocalSource.getItemList()
            .flatMap {
                if (it.isNullOrEmpty()) {
                    loadFromRemoteAndCache(itemListPrams)
                }
                Flowable.just(it.mapToItemModel())
            }
    }

    override fun reFetchItemsFromRemote(): Completable {
        return Completable.create {
          //  loadFromRemoteAndCache(itemListPrams)
            it.onComplete()
        }
    }

    private fun loadFromRemoteAndCache(itemListPrams: ItemListPrams) {
        itemListRemoteSource.getItems(itemListPrams).flatMap {
            it.items?.let {
                cacheProducts(it.mapToLocalModels()).subscribe()
            }
            return@flatMap Single.just(true)
        }.subscribe()
    }

    private fun cacheProducts(products: List<ItemLocalModel>) =
        itemListLocalSource.cacheItemList(products)

}

