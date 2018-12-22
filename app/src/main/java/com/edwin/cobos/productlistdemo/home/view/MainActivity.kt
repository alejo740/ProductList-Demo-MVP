package com.edwin.cobos.productlistdemo.home.view

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import com.edwin.cobos.productlistdemo.R
import com.edwin.cobos.productlistdemo.api.models.Product
import com.edwin.cobos.productlistdemo.app.App
import com.edwin.cobos.productlistdemo.home.presenter.HomePresenter
import com.edwin.cobos.productlistdemo.webview.WebViewActivity
import javax.inject.Inject


class MainActivity : AppCompatActivity(), HomeView {

    lateinit var rootView : ViewGroup

    private lateinit var productList: RecyclerView

    private lateinit var adapterProductList: ProductListAdapter

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).component!!.inject(this)

        rootView = findViewById(R.id.rootView)

        setupProductList()
    }

    private fun setupProductList() {
        productList = findViewById<RecyclerView>(R.id.productList)

        productList.layoutManager = LinearLayoutManager(this)

        productList.setHasFixedSize(true)
        adapterProductList = ProductListAdapter { data : String -> openWebView(data) }
        productList.adapter = adapterProductList
    }

    private fun openWebView(data: String) {
        val intent = Intent(this, WebViewActivity::class.java)
        intent.putExtra(WebViewActivity.WEB_VIEW_URL, data)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        presenter.setView(this)
        presenter.loadData()
    }

    override fun onStop() {
        super.onStop()
        presenter.rxUnsubscribe()
    }

    override fun updateProductList(product: List<Product>) {
        adapterProductList.setProducts(product)
    }

    override fun showSnackbarError(message: String) {
        Snackbar.make(rootView, message, Snackbar.LENGTH_LONG).show()
    }

}
