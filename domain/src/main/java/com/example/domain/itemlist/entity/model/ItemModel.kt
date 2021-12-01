package com.example.domain.itemlist.entity.model

import androidx.annotation.Keep

@Keep
data class ItemModel(
    var id: String,
    val name: String? = null,
    val photoUrl: String? = null,
    val description: String? = null
)
