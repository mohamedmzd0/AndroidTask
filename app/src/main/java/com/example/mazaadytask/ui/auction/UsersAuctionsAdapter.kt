package com.example.mazaadytask.ui.auction

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mazaadytask.databinding.ItemUsersAcutionBinding

class UsersAuctionsAdapter : RecyclerView.Adapter<UsersAuctionsAdapter.UsersViewHolder>() {


    inner class UsersViewHolder(private val binding: ItemUsersAcutionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UsersViewHolder(
        ItemUsersAcutionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {}
    override fun getItemCount() = 3

}