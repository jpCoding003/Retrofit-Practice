package com.tops.retrofitpractice.ViewModel

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

    private  fun fetchData(){
        viewModelScope.launch {
            val products = repository.getAllProducts()
            _productList.value = products
        }
    }
}