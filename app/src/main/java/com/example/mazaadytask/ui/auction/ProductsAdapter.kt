package com.example.mazaadytask.ui.auction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.remote.categories.Category
import com.example.mazaadytask.databinding.ItemProductsBinding
import com.example.mazaadytask.databinding.ItemTextViewBinding
import java.util.ArrayList

class ProductsAdapter : RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    inner class ProductsViewHolder(private val binding: ItemProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductsViewHolder(
        ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {}

    override fun getItemCount() = 10
}