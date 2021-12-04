package com.example.data.tags.source.remote.model

import androidx.annotation.Keep
@Keep
data class Tags(val tags: List<TagRemoteModel>? = null)

@Keep
data class TagRemoteModel(
    val tagName: String? = null,
    val photoURL: String? = null)