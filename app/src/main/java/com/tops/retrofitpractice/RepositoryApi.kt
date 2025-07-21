package com.tops.retrofitpractice

import com.tops.retrofitpractice.model.Products
import com.tops.retrofitpractice.service.ClientApi

class RepositoryApi {

    private val callApi = ClientApi.instance   // Calls the API

    suspend fun getAllProducts(): List<Products>{
        return callApi.getAll().products
    }
}