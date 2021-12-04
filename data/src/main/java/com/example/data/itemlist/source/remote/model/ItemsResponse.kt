package com.example.data.itemlist.source.remote.model

import androidx.annotation.Keep


@Keep
data class ItemsResponse(val items: List<ItemRemoteModel>? = null)
