package com.example.utils

import com.google.gson.Gson
import java.util.*


inline fun <reified R> String.fromJson(): R {
    return Gson().fromJson(this, R::class.java)
}

inline fun <reified T> String.stringToArray(clazz: Class<Array<T>>?): MutableList<Array<T>> {
    val arr = Gson().fromJson(this, clazz)
    return Arrays.asList(arr) //or return Arrays.asList(new Gson().fromJson(s, clazz)); for a one-liner
}

inline fun <reified R> R.toJson(): String {
    return Gson().toJson(this, R::class.java)
}