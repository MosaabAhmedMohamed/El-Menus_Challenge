package com.example.data.itemlist.source.local.dao

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.data.core.db.ElmenusChallengeDatabase
import com.example.data.factory.ItemListFactory
import com.example.data.itemlist.source.mapper.mapToLocalModels
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [28])
class ItemListDaoTest {

    @get:Rule
    var instantTask = InstantTaskExecutorRule()


    private lateinit var database: ElmenusChallengeDatabase

    private lateinit var dao: ItemListDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, ElmenusChallengeDatabase::class.java)
            .allowMainThreadQueries()
            .build()
        dao = database.itemListDao()
    }

    @Test
    fun getCachedItems_returnsData() {
        val items = ItemListFactory.generateDummyItemListRemoteModels(5)
        val mappedItems = items.mapToLocalModels(ItemListFactory.tagId)
        dao.deleteAllEntriesOfTag(ItemListFactory.tagId)
        dao.insertItemList(mappedItems).test()
        // Act   // Assert
        dao.getItemList(ItemListFactory.tagId)
            .test()
            .assertValue {
                it == mappedItems
            }
    }

    @Test
    fun deleteEntries_returnsEmpty() {

        dao.deleteAllEntriesOfTag(ItemListFactory.tagId)
        // Act   // Assert
        dao.getItemList(ItemListFactory.tagId)
            .test()
            .assertValue {
                it.isNullOrEmpty()
            }

    }

    @After
    fun tearDown() {
        database.close()
    }

}