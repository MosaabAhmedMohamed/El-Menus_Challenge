package com.example.presentation.tags.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.filter
import androidx.paging.rxjava2.cachedIn
import com.example.domain.tags.model.TagModel
import com.example.domain.tags.usecase.TagsUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.base.ui.NavManager
import com.example.presentation.tags.ui.fragment.TagsFragmentDirections
import io.reactivex.Flowable
import javax.inject.Inject

class TagsViewModel @Inject constructor(private val tagsUseCase: TagsUseCase) : BaseViewModel() {


    fun getTags(): Flowable<PagingData<TagModel>> {
        return tagsUseCase
            .getTags()
            .map { pagingData -> pagingData.filter { it.name != null } }
            .cachedIn(viewModelScope)
    }


    fun navigateToSelectedTag(tagName: String?) {
        tagName?.let {
            val navDirections = TagsFragmentDirections
                .actionTagsFragmentToItemListFragment(tagName)
            NavManager.navigate(navDirections)
        }
    }


}