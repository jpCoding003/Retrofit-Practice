package com.tops.retrofitpractice

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.retrofitpractice.databinding.ActivityMainBinding
import com.tops.retrofitpractice.model.RootProduct
import com.tops.retrofitpractice.service.RetrofitClient
import com.tops.retrofitpractice.service.RetrofitService
import okhttp3.Callback
import retrofit2.Call
import retrofit2.Response

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val call : Call<RootProduct> = RetrofitClient.getInstance().listProducts()
        call.enqueue(object: retrofit2.Callback<RootProduct>{
            override fun onResponse(
                call: Call<RootProduct>,
                response: Response<RootProduct>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    binding.textview.setText(data.toString())
                    Log.i(TAG, "Total Products : ${data}")
                }
            }

            override fun onFailure(
                call: Call<RootProduct?>,
                t: Throwable
            ) {
                 Log.i(TAG, t.message!!)
            }

        })
    }
}