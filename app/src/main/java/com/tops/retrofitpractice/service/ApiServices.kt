package com.tops.retrofitpractice.service

import com.tops.retrofitpractice.model.ProductsResponse
import retrofit2.http.GET

interface ApiServices {

    @GET("products")
    suspend fun getAll(): ProductsResponse

}