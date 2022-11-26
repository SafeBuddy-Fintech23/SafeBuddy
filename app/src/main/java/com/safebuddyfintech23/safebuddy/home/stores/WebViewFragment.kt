package com.safebuddyfintech23.safebuddy.home.stores

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.safebuddyfintech23.safebuddy.R

class WebViewFragment : Fragment() {

    private lateinit var myWebView: WebView
    private lateinit var webUrl: String
    private lateinit var webTitle: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //getting the Url from the bundle
        webUrl = arguments?.getString("WEB URL").toString()
        webTitle = arguments?.getString("WEB TITLE").toString()
        myWebView = view.findViewById(R.id.my_webview)
        myWebView.webViewClient = customWebViewClient
        myWebView.loadUrl(webUrl)
        myWebView.settings.javaScriptEnabled = true
    }



}

val customWebViewClient = object : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        Log.d("js page", url ?: "")
        if (url?.contains("https://www.jumia.com.ng/cart/") == true) {
            Log.d("js page", url ?: "")
            view?.evaluateJavascript(
                """
                    (function() {
                        let prods = ""
                        document.querySelectorAll(".card > .core").forEach(prod => prods += prod.innerHTML + "<**>")
                        return prods
                    })()
                """.trimMargin()
            ) { Log.d("jseval", it.toString()) }
        }
        super.onPageFinished(view, url)
    }
}