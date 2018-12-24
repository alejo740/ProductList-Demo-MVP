package com.edwin.cobos.productlistdemo.api.services

import com.edwin.cobos.productlistdemo.api.models.Product
import io.reactivex.Observable
import retrofit2.http.GET


interface ProductsApiService {

    @GET("anf/nativeapp/qa/codetest/codeTest_exploreData.json")
    fun getProducts(): Observable<List<Product>>

}