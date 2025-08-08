package com.tops.retrofitpractice.service

import com.tops.retrofitpractice.model.Product
import com.tops.retrofitpractice.model.ProductsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {

    @GET("products")
    suspend fun getAll(): ProductsResponse

    @POST("products/add")
    suspend fun addProduct(@Body products: Product): Response<Product>

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") productId: Int?): Response<Unit>
}