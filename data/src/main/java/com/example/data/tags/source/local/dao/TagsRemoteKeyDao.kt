package com.example.data.tags.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.tags.source.local.model.TagRemoteKey
import io.reactivex.Completable


@Dao
interface TagsRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(remoteKey: List<TagRemoteKey?>):Completable

    @Query("SELECT * FROM TagRemoteKey WHERE id = :id")
    fun remoteKeysByTagId(id: String): TagRemoteKey?

    @Query("DELETE FROM TagRemoteKey")
    fun clearRemoteKeys(): Completable

}