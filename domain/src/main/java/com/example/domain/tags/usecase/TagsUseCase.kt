package com.example.domain.tags.usecase

import androidx.paging.PagingData
import com.example.domain.tags.model.TagModel
import com.example.domain.tags.repository.TagsRepository
import io.reactivex.Flowable
import javax.inject.Inject

class TagsUseCase @Inject constructor(private val tagsRepository: TagsRepository) {


    fun getTags(): Flowable<PagingData<TagModel>> {
        return tagsRepository.getTags()
    }

}