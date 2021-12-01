package com.example.data.tags.source.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.tags.source.local.model.TagLocalModel
import io.reactivex.Completable
import io.reactivex.Flowable

@Dao
interface TagsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTags(products: List<TagLocalModel>): Completable

    @Query("select * from Tag")
    fun getTags(): PagingSource<Int, TagLocalModel>

    @Query("delete from Tag")
    fun deleteAllEntries(): Completable

}