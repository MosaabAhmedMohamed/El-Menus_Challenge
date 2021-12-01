package com.example.el_menus_challenge.di.itemlist

import com.example.data.core.db.ElmenusChallengeDatabase
import com.example.data.itemlist.repository.ItemListRepositoryImpl
import com.example.data.itemlist.source.local.ItemListLocalSource
import com.example.data.itemlist.source.remote.ItemListRemoteSource
import com.example.data.itemlist.source.remote.client.ItemListApi
import com.example.domain.itemlist.repository.ItemListRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ItemListModule {

    @Provides
    fun provideItemListApi(retrofit: Retrofit.Builder): ItemListApi {
        return retrofit
            .build()
            .create(ItemListApi::class.java)
    }

    @Provides
    fun provideItemListLocalSource(
        db: ElmenusChallengeDatabase
    ): ItemListLocalSource =
        ItemListLocalSource(db.itemListDao())


    @Provides
    fun provideItemListRemoteSource(
        itemListApi: ItemListApi
    ): ItemListRemoteSource =
        ItemListRemoteSource(itemListApi)

    @Provides
    fun provideItemListRepository(
        itemListRemoteSource: ItemListRemoteSource,
        itemListLocalSource: ItemListLocalSource
    ): ItemListRepository =
        ItemListRepositoryImpl(itemListRemoteSource,itemListLocalSource)

}