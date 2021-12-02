package com.example.data.itemlist.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.itemlist.source.local.model.ItemLocalModel
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface ItemListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItemList(products: List<ItemLocalModel>): Completable

    @Query("select * from ItemLocalModel")
    fun getItemList(): Flowable<List<ItemLocalModel>>

    @Query("delete from ItemLocalModel")
    fun deleteAllEntries(): Completable
}