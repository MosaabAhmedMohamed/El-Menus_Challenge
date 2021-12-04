package com.example.presentation.tags.model

import androidx.annotation.Keep

@Keep
data class TagUiModel(
    var id: String,
    var name: String? = null,
    var photoURL: String? = null
)