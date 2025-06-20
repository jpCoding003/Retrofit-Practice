package com.tops.retrofitpractice.service

import android.util.Log
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "RetrofitClient"
class RetrofitClient {

    companion object{

        private var service: RetrofitService? =null

        fun getInstance(): RetrofitService= service?:synchronized(this){
            service?: buildRetrofitService().also { service = it}
        }

        private fun buildRetrofitService(): RetrofitService {

            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            Log.i(TAG, "RetrofitClient = buildRetrofitService Called ")
            Log.i(TAG, " IS_DATA_Present_Or_Not:=  $service")
            return retrofit.create(RetrofitService::class.java)
        }

    }
}