package com.example.edwin_cobos.productlistdemo

import com.edwin.cobos.productlistdemo.api.models.Product
import com.edwin.cobos.productlistdemo.home.interactor.HomeInteractor
import com.edwin.cobos.productlistdemo.home.view.HomeView
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class HomePresenterTest {

    @Mock
    private val view: HomeView = Mockito.mock(HomeView::class.java)

    @Mock
    var interactor: HomeInteractor = Mockito.mock(HomeInteractor::class.java)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun test_load_products_success() {
        val product1 = Product(
            "Title 1", "image Url", ArrayList(), "promoMessage 1", "top description 1", "bottom description 1"
        )
        val product2 = Product(
            "Title 2", "image Url 2", ArrayList(), "promoMessage 2", "top description 2", "bottom description 2"
        )

        val products = ArrayList<Product>()
        products.add(product1)
        products.add(product2)
        Mockito.`when`(interactor.productsResult()).thenReturn(Observable.just(products))


        val obs = interactor.productsResult()

        obs.subscribeWith(object : DisposableObserver<List<Product>>() {
            override fun onComplete() {

            }

            override fun onNext(t: List<Product>) {
                Assert.assertEquals(products, t)
            }

            override fun onError(e: Throwable) {
                Assert.fail()
            }

        })


    }

}