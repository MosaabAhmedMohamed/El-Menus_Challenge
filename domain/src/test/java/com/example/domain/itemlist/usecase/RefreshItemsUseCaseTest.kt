package com.example.domain.itemlist.usecase

import com.example.domain.factory.ItemListFactory.itemListPrams
import com.example.domain.itemlist.repository.ItemListRepository
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class RefreshItemsUseCaseTest{

    @Mock
    lateinit var itemListRepository: ItemListRepository

    private lateinit var refreshItemsUseCase: RefreshItemsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        refreshItemsUseCase = RefreshItemsUseCase(itemListRepository)
    }

    @Test
    fun refreshItems_call_repository() {
        // Arrange
        // No Arrangement for this test case

        // Act
        refreshItemsUseCase.refreshItems(itemListPrams.tagName, itemListPrams.tagId)

        // Assert
        Mockito.verify(itemListRepository).reFetchItemsFromRemote(itemListPrams)
    }

    @Test
    fun getItems_completes() {
        // Arrange
        stubRefresh(Completable.complete())
        // Act // Assert
        refreshItemsUseCase
            .refreshItems(itemListPrams.tagName, itemListPrams.tagId)
            .test()
            .assertComplete()

    }

    private fun stubRefresh(completable: Completable) {
        Mockito.`when`(itemListRepository.reFetchItemsFromRemote(itemListPrams))
            .thenReturn(completable)
    }

}