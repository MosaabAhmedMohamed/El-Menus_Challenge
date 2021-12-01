package com.example.domain.tags.model

import androidx.annotation.Keep

@Keep
data class TagModel(
    var id: String,
    var name: String? = null,
    var photoURL: String? = null
)