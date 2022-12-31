package com.example.domain

import android.os.RemoteException
import android.util.AndroidException
import android.util.Log
import com.example.data.entity.BaseResponse
import com.example.data.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.transform
import retrofit2.Response
import java.io.IOException
import java.net.*

val TAG = "ResponseExt"
inline fun <T, R> Flow<Response<BaseResponse<T>>>.transformResponseData(
    crossinline onSuccess: suspend FlowCollector<R>.(T) -> Unit
): Flow<R> {
    return transform {
        val errorBody = it.errorBody()
        val body = it.body()

        when {
            it.isSuccessful && body != null && body.code in 200..299 -> {
                onSuccess(body.data!!)
            }
            it.isSuccessful && body != null && body.msg.isNullOrBlank().not() -> {
                throw Throwable(message = body.msg)
            }
            it.code() == 401 -> throw Throwable(Constants.ERROR_API.UNAUTHRIZED)
            it.code() == 500 -> throw Throwable(Constants.ERROR_API.SERVER_ERROR)
            else -> {
                Log.e(TAG, it.code().toString() + "," + errorBody.toString())
                throw Throwable().handleException()
            }
        }
        Log.e(TAG, "transformResponseData: code ${it.code()} error body ${errorBody?.string()}")

    }
}


fun Throwable.handleException(): Throwable {
    return if (this is AndroidException || this is RemoteException || this is BindException || this is PortUnreachableException || this is SocketTimeoutException || this is UnknownServiceException || this is UnknownHostException || this is IOException || this is ConnectException || this is NoRouteToHostException) {
        Throwable(Constants.ERROR_API.CONNECTION_ERROR)
    } else {
        this
    }
}
