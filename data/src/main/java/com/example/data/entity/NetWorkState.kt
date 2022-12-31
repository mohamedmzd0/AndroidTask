package com.example.data.entity


sealed class NetWorkState {
    data class Success<out T>(val data: T) : NetWorkState()
    data class Error(val th: Throwable) : NetWorkState()
    object StartLoading : NetWorkState()
    object StopLoading : NetWorkState()

}

