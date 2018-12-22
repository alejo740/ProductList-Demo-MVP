package com.edwin.cobos.productlistdemo.app

import com.edwin.cobos.productlistdemo.api.services.ProductsApiModule
import com.edwin.cobos.productlistdemo.home.di.HomeModule
import com.edwin.cobos.productlistdemo.home.view.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, ProductsApiModule::class, HomeModule::class])
interface ApplicationComponent {

    fun inject(target: MainActivity)

}