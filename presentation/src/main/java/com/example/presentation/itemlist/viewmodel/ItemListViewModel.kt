package com.example.presentation.itemlist.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.domain.itemlist.usecase.GetTagItemsUseCase
import com.example.domain.itemlist.usecase.RefreshItemsUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.base.SchedulerProvider
import com.example.presentation.base.ui.NavManager
import com.example.presentation.itemlist.model.mapper.mapToUiModels
import com.example.presentation.itemlist.model.ItemUiModel
import com.example.presentation.itemlist.ui.fragment.ItemListFragmentDirections
import com.example.presentation.itemlist.viewstate.ItemListViewState
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

    fun navigateToItemDetail(
        itemUiModel: ItemUiModel,
        img: ImageView,
        navController: NavController
    ) {
        val extras = FragmentNavigatorExtras(img to img.transitionName)
        navController.navigate(
            ItemListFragmentDirections
                .actionItemListFragmentToItemDetailFragment(itemUiModel),
            extras
        )
    }


}