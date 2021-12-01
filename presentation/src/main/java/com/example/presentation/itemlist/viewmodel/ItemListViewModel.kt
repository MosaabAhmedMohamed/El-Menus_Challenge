package com.example.presentation.itemlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import com.example.domain.itemlist.usecase.GetTagItemsUseCase
import com.example.domain.itemlist.usecase.RefreshItemsUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.base.SchedulerProvider
import com.example.presentation.base.ui.NavManager
import com.example.presentation.itemlist.mapper.mapToUiModels
import com.example.presentation.itemlist.model.ItemUiModel
import com.example.presentation.itemlist.ui.fragment.ItemListFragmentDirections
import com.example.presentation.itemlist.viewstate.ItemListViewState
import com.example.presentation.tags.ui.fragment.TagsFragmentDirections
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ItemListViewModel @Inject constructor(
    private val getTagItemsUseCase: GetTagItemsUseCase,
    private val refreshItemsUseCase: RefreshItemsUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val itemListViewStateLDPrivate by lazy { MutableLiveData<ItemListViewState>() }
    val itemListViewStateLD: LiveData<ItemListViewState> get() = itemListViewStateLDPrivate

    fun getItemList(tagName: String) {
        getTagItemsUseCase.getItems(tagName)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe {
                itemListViewStateLDPrivate.postValue(ItemListViewState.Loading)
            }
            .subscribe({
                if (!it.isNullOrEmpty())
                    itemListViewStateLDPrivate.value = ItemListViewState.onSuccess(it.mapToUiModels())
                else {
                    itemListViewStateLDPrivate.value = ItemListViewState.onEmptyState
                }
            }, {
                itemListViewStateLDPrivate.value = ItemListViewState.onError(it)
            })
            .addTo(compositeDisposable)
    }

    fun refreshItemList(tagName: String) {
        refreshItemsUseCase.refreshItems(tagName)
            .subscribeOn(schedulerProvider.io())
            .subscribe()
            .addTo(compositeDisposable)
    }

    fun navigateToItemDetail(itemUiModel: ItemUiModel) {
            val navDirections = ItemListFragmentDirections
                .actionItemListFragmentToItemDetailFragment(itemUiModel)
            NavManager.navigate(navDirections)
    }


}