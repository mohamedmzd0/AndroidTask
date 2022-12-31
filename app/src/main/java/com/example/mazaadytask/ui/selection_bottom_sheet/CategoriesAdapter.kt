package com.example.mazaadytask.ui.selection_bottom_sheet

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.data.remote.categories.Category
import com.example.mazaadytask.databinding.ItemTextViewBinding

class CategoriesAdapter : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>(),
    Filterable {
    private var arrayList = ArrayList<Category>()
    private val arrayListCopy = ArrayList<Category>()
    var onItemSelected: ((Category) -> Unit)? = null

    inner class CategoriesViewHolder(private val binding: ItemTextViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                if (adapterPosition == -1) return@setOnClickListener
                onItemSelected?.invoke(arrayList[adapterPosition])
            }
        }

        fun bind(position: Int): Unit {
            binding.root.text = arrayList[position].name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoriesViewHolder(
        ItemTextViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) =
        holder.bind(position)

    override fun getItemCount() = arrayList.size
    fun setAll(arrayList: ArrayList<Category>?) {
        this.arrayList.apply {
            clear()
            arrayListCopy.clear()
            arrayList?.let {
                addAll(it)
                arrayListCopy.addAll(it)
            }
            notifyDataSetChanged()
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.isNullOrBlank()) arrayList =
                    arrayListCopy else {
                    val filteredList = ArrayList<Category>()
                    arrayListCopy
                        .filter { it.name?.contains(constraint!!) == true }
                        .forEach { filteredList.add(it) }
                    arrayList = filteredList
                }
                return FilterResults().apply { values = arrayList }
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                notifyDataSetChanged()
            }

        }
    }


}