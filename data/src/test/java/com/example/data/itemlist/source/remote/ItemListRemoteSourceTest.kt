package com.example.data.itemlist.source.remote

import com.example.core.test.util.TestingException
import com.example.data.factory.ItemListFactory
import com.example.data.factory.ItemListFactory.itemListPrams
import com.example.data.itemlist.source.remote.client.ItemListApi
import com.example.data.itemlist.source.remote.model.ItemsResponse
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ItemListRemoteSourceTest {

    @Mock
    lateinit var api: ItemListApi

    private lateinit var itemListRemoteDataSource: ItemListRemoteSource


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        itemListRemoteDataSource = ItemListRemoteSource(api)
    }


    @Test
    fun getItemList_call_API() {
        // Arrange
        stubItemListAPI(Single.just(ItemsResponse(ItemListFactory.generateDummyItemListRemoteModels(2))))
        // Act
        itemListRemoteDataSource.getItems(itemListPrams).test()

        // Assert
        Mockito.verify(api).getItemList(itemListPrams.tagName)
    }

    @Test
    fun getItemList_returnEmptyList() {
        // Arrange
        stubItemListAPI(Single.just(ItemsResponse(listOf())))
        // Act  // Assert
        itemListRemoteDataSource
            .getItems(itemListPrams)
            .test()
            .assertValue {
                true
            }


    }

    @Test
    fun getItemList_returnError() {
        // Arrange
        val ex = TestingException()
        stubItemListAPI(Single.error(ex))
        // Act  // Assert
        itemListRemoteDataSource
            .getItems(itemListPrams)
            .test()
            .assertError(ex)


    }

    @Test
    fun getItemList_returnListOfItemList() {
        // Arrange
        val response = ItemsResponse(ItemListFactory.generateDummyItemListRemoteModels(5))
        stubItemListAPI(Single.just(response))

        // Act  // Assert
        itemListRemoteDataSource
            .getItems(itemListPrams)
            .map { it.items }
            .test()
            .assertValue {
                it == response.items
            }

    }


    @Test
    fun getItemList_completes() {
        // Arrange
        val response = ItemsResponse(ItemListFactory.generateDummyItemListRemoteModels(5))
        stubItemListAPI(Single.just(response))

        // Act  // Assert
        itemListRemoteDataSource
            .getItems(itemListPrams)
            .test()
            .assertComplete()

    }


    /**
     * Stub helper methods
     */


    private fun stubItemListAPI(single: Single<ItemsResponse>) {
        Mockito.`when`(api.getItemList(itemListPrams.tagName))
            .thenReturn(single)
    }

}