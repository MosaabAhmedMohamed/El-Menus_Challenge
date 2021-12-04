package com.example.data.itemlist.repository

import android.annotation.SuppressLint
import com.example.data.itemlist.source.local.ItemListLocalSource
import com.example.data.itemlist.source.local.model.ItemLocalModel
import com.example.data.itemlist.source.mapper.mapToItemModel
import com.example.data.itemlist.source.mapper.mapToLocalModels
import com.example.data.itemlist.source.remote.ItemListRemoteSource
import com.example.domain.itemlist.entity.model.ItemModel
import com.example.domain.itemlist.entity.prams.ItemListPrams
import com.example.domain.itemlist.repository.ItemListRepository
import io.reactivex.Flowable
import javax.inject.Inject

class ItemListRepositoryImpl @Inject constructor(
    private val itemListRemoteSource: ItemListRemoteSource,
    private val itemListLocalSource: ItemListLocalSource
) : ItemListRepository {


    override fun getItems(itemListPrams: ItemListPrams,isForceRefresh:Boolean): Flowable<List<ItemModel>> {
        return itemListLocalSource.getItemList(itemListPrams.tagId)
            .flatMap {
                if (it.isNullOrEmpty() || isForceRefresh) {
                    return@flatMap loadFromRemote(itemListPrams)
                        .flatMap {
                            handleCacheItems(it,itemListPrams.tagId)
                        }
                }
                Flowable.just(it.mapToItemModel())
            }
    }

    private fun loadFromRemote(itemListPrams: ItemListPrams): Flowable<List<ItemLocalModel>?> {
        return itemListRemoteSource.getItems(itemListPrams)
            .map { it.items?.mapToLocalModels(itemListPrams.tagId) }
            .toFlowable()
    }

    @SuppressLint("CheckResult")
    private fun handleCacheItems(
        itemLocalModels: List<ItemLocalModel>,
        tagId: String
    ): Flowable<List<ItemModel>> {
        cacheItems(itemLocalModels, tagId).subscribe()
        return itemListLocalSource.getItemList(tagId).map { it.mapToItemModel() }
    }

    private fun cacheItems(products: List<ItemLocalModel>, tagId: String) =
        itemListLocalSource.cacheItemList(products, tagId)

}

