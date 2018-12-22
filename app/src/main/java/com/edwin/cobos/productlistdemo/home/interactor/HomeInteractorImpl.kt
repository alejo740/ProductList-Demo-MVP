package com.edwin.cobos.productlistdemo.home.interactor

import com.edwin.cobos.productlistdemo.api.models.Product
import com.edwin.cobos.productlistdemo.api.repositories.ProductsRepository
import io.reactivex.Observable

class HomeInteractorImpl(private val repository: ProductsRepository) : HomeInteractor {

    override fun productsResult(): Observable<List<Product>> {
        return repository.getProductsData()
    }

}
