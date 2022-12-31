package com.example.data.remote

import com.example.data.entity.BaseResponse
import com.example.data.entity.PropertiesModel
import com.example.data.remote.categories.AllCategoriesResponse
import com.example.data.remote.categories.Category
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MainApi {

    @GET("get_all_cats")
    suspend fun getAllCat(): Response<BaseResponse<AllCategoriesResponse>>

    @GET("properties")
    suspend fun getSubCat(@Query("cat") mainCatID: Int): Response<BaseResponse<ArrayList<PropertiesModel>>>


    @GET("get-options-child/{id}")
    suspend fun getOptionsChild(@Path("id") id: Int): Response<BaseResponse<Any>>

}