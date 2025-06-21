package com.tops.retrofitpractice.Adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tops.retrofitpractice.databinding.ProductRowItemBinding
import com.tops.retrofitpractice.model.Product

class MyAdapter(private val products: List<Product>): RecyclerView.Adapter<MyAdapter.ProductViewHolder>() {
    class ProductViewHolder (val binding: ProductRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyAdapter.ProductViewHolder {
        val binding= ProductRowItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }


    override fun onBindViewHolder(holder: MyAdapter.ProductViewHolder, position: Int) {
        val product = products[position]
        holder.binding.textviewData.setText(product.title)
        holder.binding.textviewData2.setText(product.price.toString())

        Picasso.get()
            .load(product.thumbnail)
            .into(holder.binding.imageviewData)
    }

    override fun getItemCount(): Int {
        return  products.size
    }

}