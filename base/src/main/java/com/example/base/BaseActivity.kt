package com.example.base

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.data.entity.NetWorkState
import com.example.data.utils.Constants
import com.rowaad.app.data.utils.CustomErrorThrow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

abstract class BaseActivity() : AppCompatActivity() {
    var savedInstanceState: Bundle? = null


    open fun setActions() {}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        this.savedInstanceState = savedInstanceState
        setActions()

    }


    fun handleErrorGeneral(th: Throwable, func: (() -> Unit)? = null): CustomErrorThrow? {

        when (th.message) {
            Constants.ERROR_API.UNAUTHRIZED -> {
//                todo: unAuth
            }
            Constants.ERROR_API.CONNECTION_ERROR -> {
//            todo : internet connection
            }

            else -> {
                Log.d("BaseActivity", "handleErrorGeneral: ${th.cause.toString()}")
                if (th.cause is CustomErrorThrow) {
                    val cause = th.cause as CustomErrorThrow
                    return cause
                }
            }
        }
        return null
    }


    fun handleSharedFlow(
        userFlow: SharedFlow<NetWorkState>,
        onShowProgress: (() -> Unit)? = null,
        onHideProgress: (() -> Unit)? = null,
        onSuccess: (data: Any) -> Unit,
        onError: ((th: Throwable) -> Unit)? = null
    ) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                userFlow.collect { networkState ->
                    when (networkState) {
                        is NetWorkState.Success<*> -> {
                            onSuccess(networkState.data!!)
                        }
                        is NetWorkState.StartLoading -> {
                            if (onShowProgress != null) onShowProgress()
                        }
                        is NetWorkState.StopLoading -> {
                            if (onHideProgress != null) onHideProgress()
                        }
                        is NetWorkState.Error -> {
                            if (onError == null) handleErrorGeneral(networkState.th) else onError(
                                networkState.th
                            )
                        }

                        else -> {
                        }
                    }
                }
            }
        }
    }


    fun <T> startActivity(java: Class<T>) {
        startActivity(Intent(this, java))
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

}