package com.safebuddyfintech23.safebuddy.stores

import android.content.Context
import android.os.Bundle
import android.text.TextUtils.split
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.safebuddyfintech23.safebuddy.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.stream.Collectors.toList

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
        myWebView.addJavascriptInterface(WebAppInterface(this.requireContext()), "Android")
    }

    inner class WebAppInterface(private val context: Context) {

        private val products = listOf<Product>()

        @JavascriptInterface
        fun loadProduct(productStr: String) {
//            val data = productStr.split("<*>")
            val parsedData = parseStr(productStr)
            Log.d("js prod", "$parsedData")
            viewLifecycleOwner.lifecycleScope.launch(Dispatchers.Main) {

            findNavController().navigate(R.id.action_webViewFragment_to_storeProductFragment)
            }
//        Log.d("js prod", "${parsedData[0][0].split(">")[1].split('<')[0]}")

        }
    }


}

val customWebViewClient = object : WebViewClient() {
    override fun onPageFinished(view: WebView?, url: String?) {
        Log.d("js page", url ?: "")
//        if (url?.contains("https://www.jumia.com.ng/cart/") == true) {
            Log.d("js page", url ?: "")
            view?.evaluateJavascript(
                """
                    (function() {
                        const targetNode = document.querySelectorAll("main")[0]

                        const config = {
                        attributes: true,
                        childList: true,
                        subtree: true
                        }


                        const callback = (mutationList, observer) => {
                            let prods = ""
                            let checkoutButton = document.querySelector("#jm > main > div > div.sc._bt.col8.-pvs.-phm.-df.-mvs.-bg-wt > a.btn._prim.-fw")
                            if (checkoutButton != null){
                            checkoutButton.addEventListener('click', (event) => {
                                event.preventDefault()
                                let node = document.querySelectorAll("#jm > main > div > article.card-w.col8.-df.-d-co.-pvs > div")
                                if (node.length > 0) {
                                    node.forEach(prod => prods += prod.innerHTML + "<**>")
                                    Android.loadProduct(prods)
                                }
                            })
                            }
                        }

                        const observer = new MutationObserver(callback)
                        observer.observe(targetNode, config)
                    })()
                """.trimMargin()
            ) { Log.d("jseval", it.toString()) }
//        }
        super.onPageFinished(view, url)
    }
}


fun parseStr(rawData: String): List<Product> {
    val urlRegex = Regex("href=\"/\\S+\"")
    val nameRegex = Regex("<h3 class=\\\"name\">.*?<\\/h3>")
    val priceRegex = Regex("<span class=\\\"curr\">.*?<\\/span>")
//    val base64Regex = Regex("base64,.+?\\\"")
    return nameRegex.findAll(rawData)
        .zip(priceRegex.findAll(rawData)) { a, b ->
            listOf(a.value.split(">")[1].split('<')[0]
                , b.value.split(">")[1].split('<')[0]) }
        .zip(urlRegex.findAll(rawData)) { a, b ->
            a.plus(b.value.split('"')[1].split('"')[0]) }
        .map { Product(
            url = "https://www.jumia.com.ng${it[2]}",
            name = it[0],
            price = it[1],
            imageUrl = imageUrl(it[2].split('-').last().dropLast(5))
        ) }
        .toList()
}

fun imageUrl(productId: String): String {
    val reversed = productId.reversed()
    return  "https://ng.jumia.is/unsafe/fit-in/150x150/filters:fill(white)/product/" +
            "${reversed.take(2)}/${reversed.drop(2)}/1.jpg"
}

data class Product(
    val url: String,
    val name: String,
    val price: String,
    val imageUrl: String
)
//.split("<h3 class = \"name\">")[1].split("</h3>")[0]
