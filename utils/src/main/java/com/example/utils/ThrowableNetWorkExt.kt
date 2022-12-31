package com.example.utils

import android.os.RemoteException
import android.util.AndroidException
import java.io.IOException
import java.net.*
import com.example.data.utils.Constants

//check if timeout api
fun Throwable.handleException(): Throwable {
    return if (this is AndroidException || this is RemoteException || this is BindException || this is PortUnreachableException || this is SocketTimeoutException || this is UnknownServiceException || this is UnknownHostException || this is IOException || this is ConnectException || this is NoRouteToHostException) {
        Throwable(Constants.ERROR_API.CONNECTION_ERROR)
    } else {
        this
    }
}