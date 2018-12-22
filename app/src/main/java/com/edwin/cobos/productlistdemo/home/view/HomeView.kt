package com.edwin.cobos.productlistdemo.home.view

import com.edwin.cobos.productlistdemo.api.models.Product

interface HomeView {

    fun updateProductList(product: List<Product>)

    fun showSnackbarError(message: String)

}