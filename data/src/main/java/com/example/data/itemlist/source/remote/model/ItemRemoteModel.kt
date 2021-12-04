package com.example.data.itemlist.source.remote.model

import androidx.annotation.Keep

@Keep
data class ItemRemoteModel(
    val id: Long? = 0L,
    val name: String? = null,
    val photoUrl: String? = null,
    val description: String? = null)