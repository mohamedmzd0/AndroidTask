package com.example.mazaadytask.ui.auction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.remote.categories.Category
import com.example.mazaadytask.databinding.ItemImageViewSliderBinding
import com.example.mazaadytask.databinding.ItemTextViewBinding
import java.util.ArrayList

class SliderAdapter : RecyclerView.Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(private val binding: ItemImageViewSliderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SliderViewHolder(
        ItemImageViewSliderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {}

    override fun getItemCount() = 5


}