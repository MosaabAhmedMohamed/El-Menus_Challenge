package com.example.presentation.itemlist.model

import androidx.annotation.Keep
import java.io.Serializable

@Keep
data class ItemUiModel(
    var id: String,
    val name: String? = null,
    val photoUrl: String? = null,
    val description: String? = null
): Serializable
