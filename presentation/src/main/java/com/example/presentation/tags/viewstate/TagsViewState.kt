package com.example.presentation.tags.viewstate

import androidx.paging.PagingData
import com.example.domain.tags.model.TagModel

sealed class TagsViewState {
    object Loading : TagsViewState()
    object onEmptyState : TagsViewState()
    data class onSuccess(val result: PagingData<TagModel>) : TagsViewState()
    data class onError(val error: Throwable) : TagsViewState()
}