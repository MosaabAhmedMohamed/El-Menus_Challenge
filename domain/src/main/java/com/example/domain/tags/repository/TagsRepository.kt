package com.example.domain.tags.repository

import androidx.paging.PagingData
import com.example.domain.tags.model.TagModel
import io.reactivex.Flowable

interface TagsRepository {

    fun getTags(): Flowable<PagingData<TagModel>>

}