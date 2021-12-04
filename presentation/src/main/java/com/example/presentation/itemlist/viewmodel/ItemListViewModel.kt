package com.example.presentation.itemlist.viewmodel

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.fragment.FragmentNavigatorExtras
import com.example.domain.itemlist.usecase.GetTagItemsUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.base.SchedulerProvider
import com.example.presentation.itemlist.model.mapper.mapToUiModels
import com.example.presentation.itemlist.model.ItemUiModel
import com.example.presentation.itemlist.ui.fragment.ItemListFragmentDirections
import com.example.presentation.itemlist.viewstate.ItemListViewState
import com.google.android.material.appbar.AppBarLayout
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class ItemListViewModel @Inject constructor(
    private val getTagItemsUseCase: GetTagItemsUseCase,
    private val schedulerProvider: SchedulerProvider
) : BaseViewModel() {

    private val itemListViewStateLDPrivate by lazy { MutableLiveData<ItemListViewState>() }
    val itemListViewStateLD: LiveData<ItemListViewState> get() = itemListViewStateLDPrivate

    private lateinit var tagName: String
    private lateinit var tagId: String

    fun getItemList(isForceRefresh:Boolean = false) {
        getTagItemsUseCase.getItems(tagName, tagId,isForceRefresh)
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .doOnSubscribe {
                itemListViewStateLDPrivate.postValue(ItemListViewState.Loading)
            }
            .subscribe({
                if (!it.isNullOrEmpty())
                    itemListViewStateLDPrivate.value =
                        ItemListViewState.onSuccess(it.mapToUiModels())
                else {
                        itemListViewStateLDPrivate.value = ItemListViewState.onEmptyState
                }
            }, {
                itemListViewStateLDPrivate.value = ItemListViewState.onError(it)
            })
            .addTo(compositeDisposable)
    }


    fun navigateToItemDetail(
        itemUiModel: ItemUiModel,
        img: ImageView,
        appBarLayout: AppBarLayout,
        navController: NavController
    ) {
        val extras = FragmentNavigatorExtras(
            img to img.transitionName
            ,appBarLayout to appBarLayout.transitionName)
        navController.navigate(
            ItemListFragmentDirections
                .actionItemListFragmentToItemDetailFragment(itemUiModel),
            extras
        )
    }

    fun setTagInfo(tagId: String, tagName: String) {
        this.tagId = tagId
        this.tagName = tagName
    }


}