package com.example.presentation.tags.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import androidx.paging.rxjava2.cachedIn
import com.example.domain.tags.model.TagModel
import com.example.domain.tags.usecase.TagsUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.base.ui.NavManager
import com.example.presentation.tags.model.mapper.mapToUiModel
import com.example.presentation.tags.ui.fragment.TagsFragmentDirections
import com.example.presentation.tags.viewstate.TagsViewState
import io.reactivex.Flowable
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class TagsViewModel @Inject constructor(private val tagsUseCase: TagsUseCase) : BaseViewModel() {


    private val tagsViewStateLDPrivate by lazy { MutableLiveData<TagsViewState>() }
    val tagsViewStateLD: LiveData<TagsViewState> get() = tagsViewStateLDPrivate

    private fun getTags(): Flowable<PagingData<TagModel>> {
        return tagsUseCase
            .getTags()
            .map { pagingData -> pagingData.filter { it.name != null } }
            .cachedIn(viewModelScope)
    }

    fun handleLoadState(loadState: CombinedLoadStates) {
        when(loadState.refresh){
            is LoadState.NotLoading -> {}
            LoadState.Loading -> {
                tagsViewStateLDPrivate.value = TagsViewState.Loading
            }
            is LoadState.Error -> {
                tagsViewStateLDPrivate.value = TagsViewState.onError
            }
        }
        if (loadState.append.endOfPaginationReached)
        {
            tagsViewStateLDPrivate.value = TagsViewState.onEmptyState
        }
    }


    fun navigateToSelectedTag(tagName: String?) {
        tagName?.let {
            val navDirections = TagsFragmentDirections
                .actionTagsFragmentToItemListFragment(tagName)
            NavManager.navigate(navDirections)
        }
    }

    init {
        getTags().subscribe {
            val uiModels = it.map { it.mapToUiModel() }
            tagsViewStateLDPrivate.value = TagsViewState.onSuccess(uiModels)
        }.addTo(compositeDisposable)
    }

}