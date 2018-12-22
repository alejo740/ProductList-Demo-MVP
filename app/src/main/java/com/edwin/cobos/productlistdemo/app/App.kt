package com.edwin.cobos.productlistdemo.app

import android.app.Application
import com.edwin.cobos.productlistdemo.api.services.ProductsApiModule

class App : Application() {

    var component: ApplicationComponent? = null


    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .productsApiModule(ProductsApiModule())
            .build()
    }
}