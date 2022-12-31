package com.example.di

import com.example.data.repository.CategoriesRepository
import com.example.data.repository.CategoriesRepositoryImpl
import com.example.domain.use_case.CategoriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ProvideCategoriesUseCase {
    @Provides
    fun provideCategoriesUseCase(categoriesRepository: CategoriesRepositoryImpl): CategoriesUseCase {
        return CategoriesUseCase(categoriesRepository)
    }
}