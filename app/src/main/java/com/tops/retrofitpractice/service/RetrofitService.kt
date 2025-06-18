package com.tops.retrofitpractice.service

import com.tops.retrofitpractice.model.ProductRoot
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("products")
    fun listProduct(): Call<ProductRoot>
}