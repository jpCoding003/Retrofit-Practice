package com.tops.retrofitpractice.service

import com.tops.retrofitpractice.model.RootProduct
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

//    @GET("products?limit=10")
//    fun listProduct(): Call<ProductRoot>

    @GET("/products")
    fun listProducts():Call<RootProduct>
}