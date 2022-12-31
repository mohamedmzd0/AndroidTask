package com.example.mazaadytask.ui.auction

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.base.BaseViewModel

class AuctionViewModel : BaseViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}