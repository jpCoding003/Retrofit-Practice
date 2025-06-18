package com.tops.retrofitpractice

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.retrofitpractice.model.ProductRoot
import com.tops.retrofitpractice.service.RetrofitClient
import com.tops.retrofitpractice.service.RetrofitService
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val call : Call<ProductRoot> = RetrofitClient.getInstance().listProduct()
        call.enqueue(object: retrofit2.Callback<ProductRoot>{
            override fun onResponse(
                call: Call<ProductRoot?>,
                response: Response<ProductRoot?>
            ) {
                if (response.isSuccessful) {
                    val data = response.body().toString()
                    Log.i(TAG, "Total Products : ${data}")
                }
            }

            override fun onFailure(
                call: Call<ProductRoot?>,
                t: Throwable
            ) {
                 Log.i(TAG, t.message!!)
            }

        })
    }
}