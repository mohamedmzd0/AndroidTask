package com.example.mazaadytask.ui.categories_selection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.data.entity.PropertiesModel
import com.example.data.remote.categories.Category
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
            binding.edtNewValue.doOnTextChanged { text, start, before, count ->
                if (adapterPosition == -1) return@doOnTextChanged
                propertiesList[adapterPosition].options?.find { it.selected }?.otherValue =
                    text.toString()
            }
        }

        fun bind(position: Int) {
            val data = propertiesList[position]
            binding.inputLayout.hint = data.name
            binding.edittext.setText(data.options?.find { it.selected }?.name)

            if (data.options?.find { it.selected }?.id == -1) {
                binding.inputLayoutNewValue.isVisible = true
                binding.edtNewValue.setText(data.options?.find { it.selected }?.otherValue)
            } else
                binding.inputLayoutNewValue.isVisible = false


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

    fun setSelectedValue(propertiesModel: PropertiesModel, selectedOption: Category?) {
        propertiesList.find { it.id == propertiesModel.id }?.apply {
            options?.find { it.selected }?.apply { selected = false }
            options?.find { it.id == selectedOption?.id }?.apply { selected = true }
        }
      propertiesList.indexOfFirst { it.id==propertiesModel.id }.let {
          notifyItemChanged(it)
      }
    }
}