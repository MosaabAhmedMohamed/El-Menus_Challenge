package com.example.presentation.itemlist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.core.test.util.TestingException
import com.example.domain.itemlist.entity.model.ItemModel
import com.example.domain.itemlist.repository.ItemListRepository
import com.example.domain.itemlist.usecase.GetTagItemsUseCase
import com.example.domain.itemlist.usecase.RefreshItemsUseCase
import com.example.presentation.base.AppSchedulerProvider
import com.example.presentation.base.SchedulerProvider
import com.example.presentation.factory.ItemListFactory
import com.example.presentation.factory.ItemListFactory.itemListPrams
import com.example.presentation.itemlist.model.mapper.mapToUiModels
import com.example.presentation.itemlist.viewstate.ItemListViewState
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
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
class ItemListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var itemListRepository: ItemListRepository

    @Mock
    private lateinit var stateObserver: Observer<ItemListViewState>

    private lateinit var schedulerProvider: SchedulerProvider

    private lateinit var getTagItemsUseCase: GetTagItemsUseCase

    private lateinit var refreshItemsUseCase: RefreshItemsUseCase

    private lateinit var productsListViewModel: ItemListViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        setUpSchedulers()
        setUpUseCases()
        setUpViewModel()
    }

    private fun setUpSchedulers() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        schedulerProvider = AppSchedulerProvider()
    }

    private fun setUpUseCases() {
        getTagItemsUseCase =
            GetTagItemsUseCase(itemListRepository)
        refreshItemsUseCase = RefreshItemsUseCase(itemListRepository)
    }

    private fun setUpViewModel() {
        productsListViewModel = ItemListViewModel(
            getTagItemsUseCase,
            refreshItemsUseCase,
            schedulerProvider
        )
        productsListViewModel.setTagInfo(itemListPrams.tagId, itemListPrams.tagName)
        productsListViewModel.itemListViewStateLD.observeForever(stateObserver)
    }

    @Test
    fun getProductsList_returnsEmpty() {
        // Arrange
        stubFetchItems(Flowable.just(listOf()))

        // Act
        productsListViewModel.getItemList()

        // Assert
        Mockito.verify(stateObserver).onChanged(ItemListViewState.Loading)
        Mockito.verify(stateObserver).onChanged(ItemListViewState.onEmptyState)
    }

    @Test
    fun getProductsList_returnsError() {
        // Arrange
        val ex = TestingException(TestingException.GENERIC_EXCEPTION_MESSAGE)
        stubFetchItems(Flowable.error(ex))

        // Act
        productsListViewModel.getItemList()

        // Assert
        Mockito.verify(stateObserver).onChanged(ItemListViewState.Loading)
        Mockito.verify(stateObserver).onChanged(ItemListViewState.onError(ex))
    }

    @Test
    fun getProductsList_returnsData() {
        // Arrange
        val products = ItemListFactory.generateDummyItemModels(10)
        stubFetchItems(Flowable.just(products))

        // Act
        productsListViewModel.getItemList()

        // Assert
        Mockito.verify(stateObserver).onChanged(ItemListViewState.Loading)
        Mockito.verify(stateObserver).onChanged(ItemListViewState.onSuccess(products.mapToUiModels()))
    }


    @Test
    fun refreshProducts_call_Repo() {
        // Arrange
        stubRefresh(Completable.complete())

        // Act
        productsListViewModel.refreshItemList()

        // Assert
        Mockito.verify(itemListRepository).reFetchItemsFromRemote(itemListPrams)
    }


    /**
     * Stub Helpers Methods
     */

    private fun stubFetchItems(flowable: Flowable<List<ItemModel>>) {
        Mockito.`when`(getTagItemsUseCase.getItems(itemListPrams.tagName, itemListPrams.tagId))
            .thenReturn(flowable)
    }

    private fun stubRefresh(completable: Completable) {
        Mockito.`when`(itemListRepository.reFetchItemsFromRemote(itemListPrams))
            .thenReturn(completable)
    }

}