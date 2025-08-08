package com.tops.retrofitpractice

import com.tops.retrofitpractice.model.Product
import com.tops.retrofitpractice.model.Products
import com.tops.retrofitpractice.service.ClientApi
import retrofit2.Response

class RepositoryApi {

    private val callApi = ClientApi.instance   // Calls the API

    suspend fun getAllProducts(): List<Products>{
        return callApi.getAll().products
    }

    suspend fun deleteProduct(productId: Int?): Boolean {
        val response = callApi.deleteProduct(productId)
        return response.isSuccessful
    }

//    suspend fun addProduct(products: Product) = callApi.addProduct(products)
    suspend fun addProduct(products: Product): Response<Product> {
        return callApi.addProduct(products)
    }

}