package com.tops.retrofitpractice.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ClientApi {

    val instance: ApiServices by lazy {
        val retrofit = Retrofit.Builder().baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(ApiServices::class.java)
    }

}