package com.example.presentation.tags.viewstate

import androidx.paging.PagingData
import com.example.presentation.tags.model.TagUiModel

sealed class TagsViewState {
    object Loading : TagsViewState()
    object onEmptyState : TagsViewState()
    data class onSuccess(val result: PagingData<TagUiModel>) : TagsViewState()
    object onError : TagsViewState()
}