package com.edwin.cobos.productlistdemo.home.interactor

import com.edwin.cobos.productlistdemo.api.models.Product
import io.reactivex.Observable


interface HomeInteractor {

    fun productsResult(): Observable<List<Product>>

}