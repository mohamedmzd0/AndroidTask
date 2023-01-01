package com.example.domain.use_case

import android.content.Context
import com.example.data.entity.PropertiesModel
import com.example.data.remote.categories.AllCategoriesResponse
import com.example.data.remote.categories.Category
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.CategoriesRepositoryImpl
import com.example.domain.R
import com.example.domain.transformResponseData
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoriesUseCase @Inject constructor(
    private val categoriesRepository: CategoriesRepositoryImpl,
    @ApplicationContext private val context: Context
) {


    suspend fun getMainCat(): Flow<AllCategoriesResponse> {
        return categoriesRepository.getMainCat().transformResponseData { emit(it) }
    }

    suspend fun getSubCategories(id: Int): Flow<ArrayList<PropertiesModel>> {
        return categoriesRepository.getSubCategories(id)
            .transformResponseData {
                emit(it.apply {
                    onEach { it.options?.add(
                        0, Category(name = context.resources.getString(R.string.other), id = -1)
                    )}
                })
            }
    }

    suspend fun getOptionsChild(id: Int): Flow<Any> {
        return categoriesRepository.getOptionsChild(id).transformResponseData { emit(it) }
    }
}