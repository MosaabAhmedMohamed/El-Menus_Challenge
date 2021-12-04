package com.example.presentation.itemdetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.presentation.base.BaseViewModel
import com.example.presentation.itemdetail.viewstate.ItemDetailViewState
import com.example.presentation.itemlist.model.ItemUiModel
import javax.inject.Inject

class ItemDetailViewModel @Inject constructor() : BaseViewModel() {

    private val itemDetailViewStateLDPrivate by lazy { MutableLiveData<ItemDetailViewState>() }
    val itemDetailViewStateLD: LiveData<ItemDetailViewState> get() = itemDetailViewStateLDPrivate
    private lateinit var itemDetail: ItemUiModel

    fun setItemDetailModel(itemDetail: ItemUiModel) {
        this.itemDetail = itemDetail
        itemDetailViewStateLDPrivate.value = ItemDetailViewState.onSuccess(itemDetail)
    }


}