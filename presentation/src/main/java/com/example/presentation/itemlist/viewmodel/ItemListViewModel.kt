package com.example.presentation.itemlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.itemlist.usecase.GetTagItemsUseCase
import com.example.domain.itemlist.usecase.RefreshItemsUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.base.SchedulerProvider
import com.example.presentation.tags.viewstate.ItemListViewState
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
                    itemListViewStateLDPrivate.value = ItemListViewState.onSuccess(it)
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


}