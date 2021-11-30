package com.example.data.tags.source.mapper

import com.example.data.tags.source.local.model.TagLocalModel
import com.example.data.tags.source.remote.model.TagRemoteModel
import com.example.domain.tags.model.TagModel

fun List<TagRemoteModel>.mapToLocalModels(): List<TagLocalModel> {
    return this.map { it.mapToLocalModel() }
}

fun TagRemoteModel.mapToLocalModel(): TagLocalModel {
    return TagLocalModel(
        tagName =this.tagName,
        photoURL = this.photoURL
    )
}


fun List<TagLocalModel>.mapToDomainModels(): List<TagModel> {
    return this.map { it.mapToDomainModel() }
}

fun TagLocalModel.mapToDomainModel(): TagModel {
    return TagModel(
        this.id.toString(),
        this.tagName,
        this.photoURL
    )
}