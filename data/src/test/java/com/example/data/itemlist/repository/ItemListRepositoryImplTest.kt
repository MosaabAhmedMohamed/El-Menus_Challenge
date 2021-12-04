package com.example.data.itemlist.repository

import com.example.core.test.util.TestingException
import com.example.data.factory.ItemListFactory
import com.example.data.factory.ItemListFactory.itemListPrams
import com.example.data.factory.ItemListFactory.tagId
import com.example.data.itemlist.source.local.ItemListLocalSource
import com.example.data.itemlist.source.local.dao.ItemListDao
import com.example.data.itemlist.source.local.model.ItemLocalModel
import com.example.data.itemlist.source.mapper.mapToItemModel
import com.example.data.itemlist.source.mapper.mapToLocalModels
import com.example.data.itemlist.source.remote.ItemListRemoteSource
import com.example.data.itemlist.source.remote.client.ItemListApi
import com.example.data.itemlist.source.remote.model.ItemsResponse
import io.reactivex.Flowable
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ItemListRepositoryImplTest {

    @Mock
    lateinit var api: ItemListApi

    @Mock
    lateinit var itemListDao: ItemListDao

    private lateinit var itemListRepositoryImpl: ItemListRepositoryImpl

    private lateinit var itemListRemoteDataSource: ItemListRemoteSource

    private lateinit var itemListLocalDataSource: ItemListLocalSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        itemListLocalDataSource = ItemListLocalSource(itemListDao)
        itemListRemoteDataSource = ItemListRemoteSource(api)
        itemListRepositoryImpl =
            ItemListRepositoryImpl(
                itemListRemoteDataSource,
                itemListLocalDataSource
            )
    }

    @Test
    fun getItemList_call_DAO() {
        // Arrange
        stubItemListLocalDS(Flowable.just(listOf()))
        // Act
        itemListRepositoryImpl.getItems(itemListPrams).test()

        // Assert
        Mockito.verify(itemListDao).getItemList(itemListPrams.tagId)
    }

    @Test
    fun getItemList_call_API() {
        // Arrange
        stubItemListRemote(Single.just(ItemsResponse(listOf())))
        // Act
        itemListRemoteDataSource.getItems(itemListPrams).test()

        // Assert
        Mockito.verify(api).getItemList(itemListPrams.tagName)
    }

    @Test
    fun getItemList_returnEmptyList() {
        // Arrange
        stubItemListLocalDS(Flowable.just(listOf()))
        stubItemListRemote(Single.just(ItemsResponse(listOf())))
        // Act  // Assert
        itemListRepositoryImpl
            .getItems(itemListPrams)
            .test()
            .assertValue {
                it.isEmpty()
            }


    }

    @Test
    fun getItemList_returnError() {
        // Arrange
        val ex = TestingException()
        stubItemListLocalDS(Flowable.error(ex))
        // Act  // Assert
        itemListRepositoryImpl
            .getItems(itemListPrams)
            .test()
            .assertError(ex)


    }

    @Test
    fun getItemList_returnListOfItemList() {
        // Arrange
        val response = ItemsResponse(ItemListFactory.generateDummyItemListRemoteModels(5))
        stubItemListLocalDS(Flowable.just(response.items?.mapToLocalModels(tagId)))
        stubItemListRemote(Single.just(response))

        // Act  // Assert
        itemListRepositoryImpl
            .getItems(itemListPrams)
            .test()
            .assertValue(response.items?.mapToLocalModels(tagId)?.mapToItemModel())

    }


    @Test
    fun reFetchItemListFromRemote_call_API() {
        // Arrange
        // No Arrangement for this test case

        // Act
        itemListRepositoryImpl.reFetchItemsFromRemote(itemListPrams).test()

        // Assert
        Mockito.verify(api).getItemList(itemListPrams.tagName)
    }

    /**
     * Stub helper methods
     */

    private fun stubItemListRemote(single: Single<ItemsResponse>) {
        Mockito.`when`(api.getItemList(itemListPrams.tagName))
            .thenReturn(single)
    }

    private fun stubItemListLocalDS(flowable: Flowable<List<ItemLocalModel>>) {
        Mockito.`when`(itemListDao.getItemList(itemListPrams.tagId))
            .thenReturn(flowable)
    }

}