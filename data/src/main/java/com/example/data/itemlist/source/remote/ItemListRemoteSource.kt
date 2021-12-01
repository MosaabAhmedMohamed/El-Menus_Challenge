package com.example.data.itemlist.source.remote

import com.example.data.itemlist.source.remote.client.ItemListApi
import com.example.data.itemlist.source.remote.model.ItemRemoteModel
import com.example.data.itemlist.source.remote.model.ItemsResponse
import com.example.domain.itemlist.entity.prams.ItemListPrams
import io.reactivex.Single
import javax.inject.Inject

class ItemListRemoteSource@Inject constructor(
    private val itemListApi: ItemListApi) {

    fun getItems(itemListPrams: ItemListPrams): Single<ItemsResponse> {
        return itemListApi.getItemList(itemListPrams.tagName)
    }
}