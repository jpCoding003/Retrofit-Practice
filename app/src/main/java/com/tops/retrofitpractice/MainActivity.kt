package com.tops.retrofitpractice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.tops.retrofitpractice.Adapter.MyAdapter
import com.tops.retrofitpractice.ViewModel.ProductsViewModel
import com.tops.retrofitpractice.databinding.ActivityMainBinding
import com.tops.retrofitpractice.model.Products

private const val TAG = "MainActivity"
class MainActivity : AppCompatActivity(),MyAdapter.OnProductLongClickListener {

    private lateinit var adapter: MyAdapter
    private val productviewmodel: ProductsViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(binding.toolbarview)
        productviewmodel.fetchData()
        adapter = MyAdapter(mutableListOf(),this)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
        productviewmodel.productList.observe(this, Observer{
            list-> if (list.isNotEmpty()) {
            adapter.submitlist(list)
            Log.i("ListFromViewmodel", "This Is mainactivity $list")
        }else{
            Toast.makeText(this, "List Is Empty", Toast.LENGTH_LONG).show()
        }
        })
    }

    override fun onProductLongClick(product: Products) {
        Toast.makeText(this, "Deleting ${product.title}", Toast.LENGTH_SHORT).show()
        productviewmodel.deleteProductById(product.id)
    }

}