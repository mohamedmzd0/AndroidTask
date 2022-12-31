package com.example.base

import android.content.Context
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


open class BaseBottomSheet : BottomSheetDialogFragment() {
    lateinit var _context: Context
    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
    }

    override fun getTheme(): Int {
        return R.style.transparentBottomSheetDialogTheme
    }
}