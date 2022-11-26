package com.safebuddyfintech23.safebuddy.home.stores

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
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
        myWebView.loadUrl(webUrl)
    }


}