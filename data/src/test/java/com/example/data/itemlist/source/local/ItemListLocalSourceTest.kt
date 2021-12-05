package com.example.data.itemlist.source.local

import com.example.data.factory.ItemListFactory.generateDummyItemListLocalModels
import com.example.data.factory.ItemListFactory.tagId
import com.example.data.itemlist.source.local.dao.ItemListDao
import com.example.data.itemlist.source.local.model.ItemLocalModel
import io.reactivex.Completable
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ItemListLocalSourceTest{

    @Mock
    lateinit var itemListDao: ItemListDao

    private lateinit var itemListLocalDataSource: ItemListLocalSource

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        itemListLocalDataSource = ItemListLocalSource(itemListDao)
    }


    @Test
    fun cacheItems_call_dao() {
        // Arrange
        // No Arrangement for this test case
        val items = generateDummyItemListLocalModels(5)
        // Act
        itemListLocalDataSource
            .cacheItemList(items,tagId)
            .test()

        // Assert
        Mockito.verify(itemListDao).deleteAllEntriesOfTag(tagId)
        Mockito.verify(itemListDao).insertItemList(items)
    }

    @Test
    fun cacheProducts_completes() {
        // Arrange

        stubCacheItems(Completable.complete())
        // Act // Assert
        itemListLocalDataSource.cacheItemList(generateDummyItemListLocalModels(5),tagId)
            .test()
            .assertComplete()

    }

    @Test
    fun getCachedItems_returnsData() {
        // Arrange
        val items = generateDummyItemListLocalModels(5)
        stubGetItems(Flowable.just(items))

        // Act   // Assert
        itemListLocalDataSource
            .getItemList(tagId)
            .test()
            .assertValue(items)

    }

    private fun stubGetItems(flowable: Flowable<List<ItemLocalModel>>) {
        Mockito.`when`(itemListDao.getItemList(tagId))
            .thenReturn(flowable)
    }

    private fun stubCacheItems(completable: Completable) {
        Mockito.`when`(itemListDao.insertItemList(generateDummyItemListLocalModels(5)))
            .thenReturn(completable)
    }



}