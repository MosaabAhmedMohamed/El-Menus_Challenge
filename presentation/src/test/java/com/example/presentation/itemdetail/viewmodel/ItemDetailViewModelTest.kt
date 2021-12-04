package com.example.presentation.itemdetail.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.presentation.factory.ItemListFactory
import com.example.presentation.itemdetail.viewstate.ItemDetailViewState
import com.example.presentation.itemlist.model.mapper.mapToUiModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class ItemDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var stateObserver: Observer<ItemDetailViewState>

    private lateinit var itemDetailViewModel: ItemDetailViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        setUpViewModel()
    }

    private fun setUpViewModel() {
        itemDetailViewModel = ItemDetailViewModel()
        itemDetailViewModel.itemDetailViewStateLD.observeForever(stateObserver)

    }


    @Test
    fun getItem_returnsData() {
        // Arrange
        val itemDetail = ItemListFactory.generateItemModel().mapToUiModel()

        // Act
        itemDetailViewModel.setItemDetailModel(itemDetail)

        // Assert
        Mockito.verify(stateObserver).onChanged(ItemDetailViewState.onSuccess(itemDetail))
    }



}