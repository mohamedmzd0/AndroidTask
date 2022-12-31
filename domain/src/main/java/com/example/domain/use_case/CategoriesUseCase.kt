package com.example.domain.use_case

import com.example.data.entity.PropertiesModel
import com.example.data.remote.categories.AllCategoriesResponse
import com.example.data.remote.categories.Category
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.CategoriesRepositoryImpl
import com.example.domain.transformResponseData
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(private val categoriesRepository: CategoriesRepositoryImpl) {


    suspend fun getMainCat(): Flow<AllCategoriesResponse> {
        return categoriesRepository.getMainCat().transformResponseData { emit(it) }
    }

    suspend fun getSubCategories(id: Int): Flow<ArrayList<PropertiesModel>> {
        return categoriesRepository.getSubCategories(id).transformResponseData { emit(it) }
    }

    suspend fun getOptionsChild(id: Int): Flow<Any> {
        return categoriesRepository.getOptionsChild(id).transformResponseData { emit(it) }
    }
}