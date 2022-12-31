package com.example.base

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.example.data.entity.NetWorkState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow


open class BaseFragment(@LayoutRes layout: Int) : Fragment(layout) {
    lateinit var _context: Context
    private var baseActivity: BaseActivity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        _context = context
        if (activity is BaseActivity)
            baseActivity = activity as BaseActivity?
    }

    override fun onDetach() {
        super.onDetach()
        baseActivity = null
    }

    protected fun handleErrorGeneral(th: Throwable, func: (() -> Unit)? = null) {
        baseActivity?.handleErrorGeneral(th, func)
    }

    protected fun handleSharedFlow(
        userFlow: SharedFlow<NetWorkState>,
        onShowProgress: (() -> Unit)? = null,
        onHideProgress: (() -> Unit)? = null,
        onSuccess: (data: Any) -> Unit,
        onError: ((th: Throwable) -> Unit)? = null
    ) {
        baseActivity?.handleSharedFlow(userFlow, onShowProgress, onHideProgress, onSuccess, onError)
    }

}