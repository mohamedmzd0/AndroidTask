package com.example.di

import com.example.data.remote.MainApi
import com.example.data.repository.CategoriesRepository
import com.example.data.repository.CategoriesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
 class CategoryRepoProvider {
/*    @Provides
    fun provideCategoryRepo(api: MainApi): CategoriesRepository{
        return CategoriesRepositoryImpl(mainApi = api)
    }*/
}