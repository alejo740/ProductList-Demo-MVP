package com.example.edwin_cobos.productlistdemo

import com.edwin.cobos.productlistdemo.api.models.Content
import com.edwin.cobos.productlistdemo.api.models.Product
import com.edwin.cobos.productlistdemo.home.interactor.HomeInteractor
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class HomePresenterTest {

    @Mock
    private lateinit var interactor: HomeInteractor

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun showProductIntoView() {
        val product = Product("Title 1", "image Url", ArrayList(), "promoMessage 1", "top description 1", "bottom description 1")

        //when(interactor.productsResult()).
    }

}