package com.safebuddyfintech23.safebuddy.home.stores

import com.safebuddyfintech23.safebuddy.R

/**
 * This class contains data that will be used in the stores package
 */
class StoreData {
    fun loadStores(): List<StoresModel> {
        return listOf(
            StoresModel(R.drawable.jumia_logo, R.string.jumia),
            StoresModel(R.drawable.amazon_logo, R.string.amazon),
            StoresModel(R.drawable.ebay_logo, R.string.ebay),
            StoresModel(R.drawable.aliexpress_logo, R.string.aliexpress),
            //StoresModel(R.drawable.shopify_logo, R.string.shopify),
        )
    }

    //stores website links
    fun loadWebsites(): List<String> {
        return listOf(
            "https://www.jumia.com.ng/",
            "https://www.amazon.com/",
            "https://www.ebay.com/",
            "https://www.aliexpress.com/",
            //StoresWebLink("https://www.shopify.com/"),
        )
    }

    //titles for the webview fragment
    fun loadTitles(): List<String> {
        return listOf(
            "Jumia",
            "Amazon",
            "eBay",
            "AliExpress",
            //Shopify,
        )
    }
}