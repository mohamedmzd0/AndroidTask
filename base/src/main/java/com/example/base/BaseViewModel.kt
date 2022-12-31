package com.example.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.entity.NetWorkState
import com.example.data.utils.Constants
import com.example.utils.handleException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


open class BaseViewModel : ViewModel() {
    private val parentJob = Job()

    private val _unAuthorizedFlow = MutableSharedFlow<Boolean>()
    internal val unAuthorizedFlow = _unAuthorizedFlow.asSharedFlow()
    private val _connectionErrorFlow = MutableSharedFlow<Boolean>()
    internal val connectionErrorFlow = _connectionErrorFlow.asSharedFlow()

    fun createCoroutinesThread(
        state: MutableSharedFlow<NetWorkState>,
        action: suspend () -> Unit
    ) {
        viewModelScope.launch(handlerShared(state)) {
            action()
        }
    }

    private fun handlerShared(state: MutableSharedFlow<NetWorkState>): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            //Log.e("error..",throwable.message.toString())
            when (throwable.message) {
                Constants.ERROR_API.UNAUTHRIZED -> {
                    _unAuthorizedFlow.tryEmit(true)
                }
                Constants.ERROR_API.CONNECTION_ERROR -> {
                    _connectionErrorFlow.tryEmit(true)
                }
                else -> {
                    state.tryEmit(NetWorkState.Error(throwable.handleException()))
                }

            }
        }

    }


    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }

    protected suspend fun <T> executeApi(
        flow: MutableSharedFlow<NetWorkState>,
        request: Flow<T>
    ) {
        request.onStart { flow.emit(NetWorkState.StartLoading) }
            .onCompletion { flow.emit(NetWorkState.StopLoading) }
            .catch { flow.emit(NetWorkState.Error(it)) }
            .collectLatest { flow.emit(NetWorkState.Success(it)) }
    }
}