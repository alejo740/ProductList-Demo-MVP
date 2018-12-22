package com.edwin.cobos.productlistdemo.home.presenter

import com.edwin.cobos.productlistdemo.api.models.Product
import com.edwin.cobos.productlistdemo.home.interactor.HomeInteractor
import com.edwin.cobos.productlistdemo.home.view.HomeView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers


class HomePresenterImpl(private val interactor: HomeInteractor) : HomePresenter {

    private var subscription: Disposable? = null
    private lateinit var view: HomeView

    override fun loadData() {
        subscription = interactor
            .productsResult()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Product>>() {
                override fun onComplete() {

                }

                override fun onNext(t: List<Product>) {
                    view.updateProductList(t)
                    view.showSnackbarError("Excellent!!!!")
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    view.showSnackbarError("Error getting products")
                }
            })
    }

    override fun rxUnsubscribe() {
        if (subscription != null) {
            if (!subscription!!.isDisposed) {
                subscription!!.dispose()
            }
        }
    }


    override fun setView(view: HomeView) {
        this.view = view
    }

}
