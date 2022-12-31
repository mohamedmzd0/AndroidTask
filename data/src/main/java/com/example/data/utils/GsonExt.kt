package com.rowaad.app.data.utils

import android.util.Log
import okhttp3.ResponseBody
import org.json.JSONObject


fun ResponseBody.handleError(): String {

    val json = JSONObject(this.string())
    Log.d("error-body", "handleError: $json")
    //{"data":{"errors":[{"key":"phone","value":"The selected Phone is invalid."}]}}
    return if (json.has("data")  && json.getJSONObject("data")
            .has("errors")
    ) {
        val errors = json.getJSONObject("data").getJSONArray("errors").getJSONObject(0)
        errors.getString("value")
    }else if (json.has("data")  && json.getJSONObject("data")
            .has("message")
    ) {
        val errors = json.getJSONObject("data")
        errors.getString("message")
    } else if (json.has("data")  && json.getJSONObject("data")
            .has("value")
    ) {
        val errors = json.getJSONObject("data")
        errors.getString("value")
    } else if (json.has("errors")) {
        val errors = json.getJSONArray("errors").getJSONObject(0)
        val errorMessage = errors.getString("value")
        val key = errors.getString("key")
        errorMessage
    } else if (json.has("error")) {
        val jsonError = json.getString("error")
        //val errorMessage = jsonError.getString("error")
        jsonError
    } else if (json.has("message")) {
        val jsonError = json.getString("message")
        //val errorMessage = jsonError.getString("error")
        jsonError
    } else {
        val jsonError = json.getJSONArray("error").getJSONObject(0)
        val errorMessage = jsonError.getString("value")
        errorMessage

    }
}

data class CustomErrorThrow(val key: String, val value: String) : Throwable()
