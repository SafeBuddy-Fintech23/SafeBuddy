package com.safebuddyfintech23.safebuddy.stores

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoreProductViewModel : ViewModel() {
    private val mutableProducts: MutableLiveData<List<Product>> = MutableLiveData()
    val products: LiveData<List<Product>> get() = mutableProducts

    fun setProducts(products: List<Product>) {
        viewModelScope.launch(Dispatchers.Main) {
            mutableProducts.value = products
        }
    }
}