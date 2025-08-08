package com.tops.retrofitpractice.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.tops.retrofitpractice.RepositoryApi
import com.tops.retrofitpractice.model.Product
import com.tops.retrofitpractice.model.Products
import com.tops.retrofitpractice.service.ApiServices
import com.tops.retrofitpractice.service.ClientApi
import kotlinx.coroutines.launch

class ProductsViewModel(application: Application) : AndroidViewModel(application) {

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
                Log.i("ListFromViewmodel", "This Is Viewmodel  $products")
                _productList.value = products
            } catch (e: Exception) {
                Log.e("FetchError", e.toString())
            }
        }
    }

    fun addProduct(product: Product,onResult: (Int) -> Unit) {
        viewModelScope.launch {
//            repository.addProduct(product)
            val response = repository.addProduct(product)
            onResult(response.code()) // Send HTTP status code back
            Log.i("Api", "Response Status=== ${response.code()}")
            if (response.isSuccessful) {
                fetchData() // Refresh list only if success
            }

            fetchData() // Refresh list after adding
        }
    }


    fun deleteProductById(productId: Int?) {
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