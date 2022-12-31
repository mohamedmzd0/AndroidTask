package com.example.mazaadytask

import android.app.Application
import com.example.data.utils.Constants
import com.example.mazaadytask.BuildConfig
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        Constants.BASE_URL= BuildConfig.BASE_URL
    }
}