package com.example.presentation.tags.model.mapper

import com.example.domain.tags.model.TagModel
import com.example.presentation.tags.model.TagUiModel

fun List<TagModel>.mapToUiModels(): List<TagUiModel> {
    return this.map { it.mapToUiModel() }
}

fun TagModel.mapToUiModel(): TagUiModel {
    return TagUiModel(
        this.id,
        this.name,
        this.photoURL
    )
}