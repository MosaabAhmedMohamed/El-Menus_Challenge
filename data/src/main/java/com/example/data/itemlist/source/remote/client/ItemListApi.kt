package com.example.data.itemlist.source.remote.client

import com.example.data.itemlist.source.remote.model.ItemsResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ItemListApi {

    @GET("/items/{TagName}")
    fun getItemList(@Path("TagName") tagName: String): Single<ItemsResponse>
}