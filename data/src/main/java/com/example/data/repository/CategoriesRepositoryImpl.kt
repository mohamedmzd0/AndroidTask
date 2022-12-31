package com.example.data.repository

import com.example.data.remote.MainApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class CategoriesRepositoryImpl @Inject constructor(private val mainApi: MainApi) :
    CategoriesRepository {
    override suspend fun getMainCat() = flow { emit(mainApi.getAllCat()) }

    override suspend fun getSubCategories(id: Int) = flow { emit(mainApi.getSubCat(id)) }

    override suspend fun getOptionsChild(id: Int) = flow { emit(mainApi.getOptionsChild(id)) }
}