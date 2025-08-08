package com.tops.retrofitpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tops.retrofitpractice.ViewModel.ProductsViewModel
import com.tops.retrofitpractice.databinding.ActivityNewProductBinding
import com.tops.retrofitpractice.model.Product

class NewProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewProductBinding
    private val productsviewmodel: ProductsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewProductBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnAddProduct.setOnClickListener {
            val productTitle = binding.etProductTitle.text.toString()
            val productDescription = binding.etProductDescription.text.toString()
            val productCategory = binding.etProductCategory.text.toString()
            val productPrice = binding.etProductPrice.text.toString().toDouble()
            val newProduct =
                Product(
                    title = productTitle,
                    description = productDescription,
                    category = productCategory,
                    price = productPrice
                )

            productsviewmodel.addProduct(newProduct){ statusCode ->
                if (statusCode == 200 || statusCode == 201 || statusCode == 202) {
                    Toast.makeText(this, "Product Added Successfully", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "❌ Failed to Add Product (Code: $statusCode)", Toast.LENGTH_SHORT).show()
                }
            }

//            val intent = Intent(this, MainActivity::class.java)
//            startActivity(intent)
//            finish()
        }
    }
}