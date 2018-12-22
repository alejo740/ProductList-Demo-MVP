package com.edwin.cobos.productlistdemo.webview

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat.startActivity
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.edwin.cobos.productlistdemo.api.services.ProductsApiModule

class WebViewActivity : AppCompatActivity() {

    private lateinit var shoppingWebView: WebView
    private lateinit var url: String

    companion object {
        const val WEB_VIEW_URL: String = "WEB_VIEW_URL"
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        url = intent.getStringExtra(WEB_VIEW_URL)

        shoppingWebView = WebView(this)

        shoppingWebView.webViewClient = ShoppingWebViewClient()

        val settings = shoppingWebView.settings

        settings.javaScriptEnabled = true

        setContentView(shoppingWebView)
    }

    override fun onResume() {
        super.onResume()
        shoppingWebView.loadUrl(url)
    }

    private class ShoppingWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return false
        }
    }
}
