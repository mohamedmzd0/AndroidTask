package com.example.mazaadytask.ui.categories_selection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.PropertiesModel
import com.example.mazaadytask.databinding.ItemInputEdttextBinding

class SubCatOptionsAdapter : RecyclerView.Adapter<SubCatOptionsAdapter.ProductsViewHolder>() {
    private val propertiesList = ArrayList<PropertiesModel>()
    var onItemClicked: ((PropertiesModel) -> Unit)? = null

    inner class ProductsViewHolder(private val binding: ItemInputEdttextBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.edittext.setOnClickListener {
                if (adapterPosition == -1) return@setOnClickListener
                onItemClicked?.invoke(propertiesList[adapterPosition])
            }
        }

        fun bind(position: Int) {
            val data = propertiesList[position]
            binding.inputLayout.hint = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ProductsViewHolder(
        ItemInputEdttextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) = holder.bind(position)

    override fun getItemCount() = propertiesList.size
    fun setAll(propertiesModels: ArrayList<PropertiesModel>?) {
        this.propertiesList.apply {
            clear()
            propertiesModels?.let { addAll(it) }
            notifyDataSetChanged()
        }
    }
}