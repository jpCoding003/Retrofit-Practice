package com.tops.retrofitpractice.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.retrofitpractice.databinding.ItemProductBinding
import com.tops.retrofitpractice.model.Products

class MyAdapter(private var productslist : List<Products>) : RecyclerView.Adapter<MyAdapter.ProductsViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductsViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ProductsViewHolder,
        position: Int
    ) {
        val products = productslist[position]
       holder.binding.tvProducts.text = products.title
        holder.binding.tvprice.text = products.price.toString()
    }

    override fun getItemCount(): Int = productslist.size

    fun submitlist(list: List<Products>){
        productslist = list
        notifyDataSetChanged()
    }
    class ProductsViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)


}