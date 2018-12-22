package com.edwin.cobos.productlistdemo.api.repositories

import com.edwin.cobos.productlistdemo.api.services.ProductsApiService
import com.edwin.cobos.productlistdemo.api.models.Product
import io.reactivex.Observable

class ProductsRepositoryImpl(private val productsApiService: ProductsApiService) : ProductsRepository {

    override fun getProductsData(): Observable<List<Product>> {
        return productsApiService.getProducts()
    }
}