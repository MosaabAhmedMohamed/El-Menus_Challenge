package com.example.data.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.itemlist.source.local.dao.ItemListDao
import com.example.data.itemlist.source.local.model.ItemLocalModel
import com.example.data.tags.source.local.dao.TagsDao
import com.example.data.tags.source.local.dao.TagsRemoteKeyDao
import com.example.data.tags.source.local.model.TagLocalModel
import com.example.data.tags.source.local.model.TagRemoteKey

@Database(
    entities = [TagLocalModel::class, TagRemoteKey::class, ItemLocalModel::class],
    version = ELMENUSChallenge_DATABASE_VERSION_NUMBER
)


abstract class ElmenusChallengeDatabase : RoomDatabase() {

    abstract fun tagsDao(): TagsDao

    abstract fun tagsRemoteKeyDao(): TagsRemoteKeyDao

    abstract fun itemListDao(): ItemListDao


}

const val ELMENUSChallenge_DATABASE_VERSION_NUMBER = 8

