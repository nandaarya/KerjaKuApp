package com.example.kerjakuapp.di

import android.content.Context
import com.example.kerjakuapp.data.Repository
import com.example.kerjakuapp.data.datastore.UserPreference
import com.example.kerjakuapp.data.datastore.dataStore
import com.example.kerjakuapp.data.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = UserPreference.getInstance(context.dataStore)
        val apiService = ApiConfig.getApiService()
        return Repository.getInstance(apiService, pref)
    }
}