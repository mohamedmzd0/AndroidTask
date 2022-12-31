package com.example.mazaadytask.ui.auction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.base.BaseFragment
import com.example.mazaadytask.R
import com.example.mazaadytask.databinding.FragmentAuctionBinding

class AuctionFragment : BaseFragment(R.layout.fragment_auction) {

    private var _binding: FragmentAuctionBinding? = null

    private val binding get() = _binding!!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentAuctionBinding.bind(view)
        setupSlider()
        setupProductsList()
        setupUsersList()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupSlider() {
        binding.viewpagerSlider.adapter = SliderAdapter()
        binding.dotIndicator.setViewPager2(viewPager2 = binding.viewpagerSlider)
    }

    private fun setupProductsList() {
        binding.recyclerViewProducts.adapter = ProductsAdapter()
    }
    private fun setupUsersList() {
        binding.recylcerViewAuctioners.adapter = UsersAuctionsAdapter()
    }
}