package com.example.kamino

import com.example.kamino.repositories.DataRepository
import com.example.kamino.ui.ui.MainPresenterImplementation
import com.example.kamino.ui.ui.MainViewPresenterContract

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.BDDMockito.then
import org.mockito.Mock
import org.mockito.junit.MockitoJUnit

class MainPresenterTest {
    private lateinit var presenter: MainPresenterImplementation
    @JvmField
    @Rule
    var mockitoRule = MockitoJUnit.rule()
    @Mock
    private lateinit var mockDataRepository: DataRepository
    @Mock
    private lateinit var mockView: MainViewPresenterContract.ViewInterface

    @Before
    fun setUp() {
        presenter = MainPresenterImplementation(mockView, mockDataRepository)
    }

    @Test
    fun when_getting_planet_then_show_loading() {
        //when
        presenter.getPlanetData()

        //then
        then(mockView).should().showProgressbar()
    }

    /* @Test
     fun addition_isCorrect() {
         Assert.assertEquals(4, 2 + 2)
     }*/
}