package com.example.data.tags.source.local.model

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "Tag")
class TagLocalModel (

    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true) val id: Int = 0,

    @ColumnInfo(name = "tagName")
    var tagName: String? = null,

    @ColumnInfo(name = "photoURL")
    var photoURL: String? = null
)