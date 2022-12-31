package com.example.data.repository

import com.example.data.entity.BaseResponse
import com.example.data.entity.PropertiesModel
import com.example.data.remote.categories.AllCategoriesResponse
import com.example.data.remote.categories.Category
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface CategoriesRepository {
    suspend fun getMainCat(): Flow<Response<BaseResponse<AllCategoriesResponse>>>
    suspend fun getSubCategories(id: Int): Flow<Response<BaseResponse<ArrayList<PropertiesModel>>>>
    suspend fun getOptionsChild(id: Int): Flow<Response<BaseResponse<Any>>>
}