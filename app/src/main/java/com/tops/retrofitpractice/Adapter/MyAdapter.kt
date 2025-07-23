package com.tops.retrofitpractice.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tops.retrofitpractice.MainActivity
import com.tops.retrofitpractice.databinding.ItemProductBinding
import com.tops.retrofitpractice.model.Products


class MyAdapter(private var productslist: MutableList<Products>,
                private val listener: OnProductLongClickListener
) : RecyclerView.Adapter<MyAdapter.ProductsViewHolder>() {

    interface OnProductLongClickListener {
        fun onProductLongClick(product: Products)
    }

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

        holder.binding.root.setOnLongClickListener {
            listener.onProductLongClick(products)
            true
        }
    }

    override fun getItemCount(): Int = productslist.size

    fun submitlist(list: List<Products>){
        productslist.clear()
        productslist = list.toMutableList()
        notifyDataSetChanged()
    }
    class ProductsViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)


}