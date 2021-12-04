package com.example.domain.itemlist.usecase

import com.example.core.test.util.TestingException
import com.example.domain.factory.ItemListFactory
import com.example.domain.factory.ItemListFactory.itemListPrams
import com.example.domain.itemlist.entity.model.ItemModel
import com.example.domain.itemlist.repository.ItemListRepository
import io.reactivex.Flowable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class GetTagItemsUseCaseTest {

    @Mock
    lateinit var itemListRepository: ItemListRepository

    private
    lateinit var useCase: GetTagItemsUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        useCase = GetTagItemsUseCase(itemListRepository)
    }

    @Test
    fun getItemList_call_repository() {
        // Arrange
        // No Arrangement for this test case

        // Act
        useCase.getItems(itemListPrams.tagName, itemListPrams.tagId, false)

        // Assert
        Mockito.verify(itemListRepository).getItems(itemListPrams)
    }

    @Test
    fun getItemList_completes() {
        // Arrange
        stubItemList(Flowable.just(ItemListFactory.generateDummyItemModels(3)))

        // Act // Assert
         useCase
             .getItems(itemListPrams.tagName, itemListPrams.tagId, false)
            .test()
            .assertComplete()

    }

    @Test
    fun getItemList_returnsData() {
        // Arrange
        val products = ItemListFactory.generateDummyItemModels(4)
        stubItemList(Flowable.just(products))

        // Act   // Assert
        useCase
            .getItems(itemListPrams.tagName, itemListPrams.tagId, false)
            .test()
            .assertValue(products)

    }

    @Test
    fun getItemList_returnsError() {
        // Arrange
        val ex = TestingException()
        stubItemList(Flowable.error(ex))

        // Act   // Assert
        useCase
            .getItems(itemListPrams.tagName, itemListPrams.tagId, false)
            .test()
            .assertError {
                it == ex
            }

    }


    private fun stubItemList(flowable: Flowable<List<ItemModel>>) {
        Mockito.`when`(itemListRepository.getItems(itemListPrams))
            .thenReturn(flowable)
    }
}