package com.safebuddyfintech23.safebuddy.home.stores

import com.safebuddyfintech23.safebuddy.R

class StoreData {
    fun loadStores(): List<StoresModel> {
        return listOf(
            StoresModel(R.drawable.jumia_logo, R.string.jumia),
            StoresModel(R.drawable.amazon_logo, R.string.amazon),
            StoresModel(R.drawable.ebay_logo, R.string.ebay),
            StoresModel(R.drawable.aliexpress_logo, R.string.aliexpress),
            StoresModel(R.drawable.shopify_logo, R.string.shopify),
        )
    }
}