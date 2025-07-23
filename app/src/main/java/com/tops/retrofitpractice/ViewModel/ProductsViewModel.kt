package com.tops.retrofitpractice.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tops.retrofitpractice.RepositoryApi
import com.tops.retrofitpractice.model.Products
import kotlinx.coroutines.launch

class ProductsViewModel : ViewModel() {

    private val repository = RepositoryApi()

    private val _productList = MutableLiveData<List<Products>>()
    val productList: LiveData<List<Products>> = _productList

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            try {
                val products = repository.getAllProducts()
                _productList.value = products
            } catch (e: Exception) {
                Log.e("FetchError", e.toString())
            }
        }
    }

    fun deleteProductById(productId: Int) {
        viewModelScope.launch {
            try {
                val isDeleted = repository.deleteProduct(productId)
                if (isDeleted) {
                  _productList.value = _productList.value?.filter { it.id != productId }
                } else {
                    Log.e("DeleteError", "Deletion failed for product id $productId")
                }
            } catch (e: Exception) {
                Log.e("DeleteException", e.toString())
            }
        }
    }
}