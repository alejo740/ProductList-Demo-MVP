package com.edwin.cobos.productlistdemo.home.presenter

import com.edwin.cobos.productlistdemo.home.view.HomeView

interface HomePresenter {

    fun loadData()

    fun rxUnsubscribe()

    fun setView(view: HomeView)

}