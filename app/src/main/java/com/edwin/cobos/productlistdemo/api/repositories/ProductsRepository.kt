package com.edwin.cobos.productlistdemo.api.repositories

import com.edwin.cobos.productlistdemo.api.models.Product
import io.reactivex.Observable

interface ProductsRepository {

    fun getProductsData(): Observable<List<Product>>

}