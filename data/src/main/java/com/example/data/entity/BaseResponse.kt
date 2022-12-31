package com.example.data.entity

data class BaseResponse<T>(
    val code: Int? = null,
    val msg: String? = "",
    val data: T? = null
)
