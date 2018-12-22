package com.edwin.cobos.productlistdemo.home.di

import com.edwin.cobos.productlistdemo.api.repositories.ProductsRepository
import com.edwin.cobos.productlistdemo.api.repositories.ProductsRepositoryImpl
import com.edwin.cobos.productlistdemo.api.services.ProductsApiService
import com.edwin.cobos.productlistdemo.home.interactor.HomeInteractor
import com.edwin.cobos.productlistdemo.home.interactor.HomeInteractorImpl
import com.edwin.cobos.productlistdemo.home.presenter.HomePresenter
import com.edwin.cobos.productlistdemo.home.presenter.HomePresenterImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class HomeModule {

    @Provides
    fun provideMainActivityHomePresenter(interactor: HomeInteractor): HomePresenter {
        return HomePresenterImpl(interactor)
    }

    @Provides
    fun provideMainActivityInteractor(repository: ProductsRepository): HomeInteractor {
        return HomeInteractorImpl(repository)
    }

    @Singleton
    @Provides
    fun provideRepo(productsApiService: ProductsApiService): ProductsRepository {
        return ProductsRepositoryImpl(productsApiService)
    }
}