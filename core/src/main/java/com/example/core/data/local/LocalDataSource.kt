package com.example.core.data.local

import android.util.Log
import com.example.core.data.local.datastore.UserPreferences
import com.example.core.domain.model.User
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val userPreferences: UserPreferences) {
    suspend fun saveSession(user: User) {
        val userModel = DataMapper.mapDomainToDataStoreModel(user)
        Log.d("user in localdatasource", user.toString())
        userPreferences.saveSession(userModel)
    }

    fun getSession(): Flow<User> {
        Log.d("user get session before", userPreferences.getSession().toString())
        val user = userPreferences.getSession().map {
            DataMapper.mapDataStoreModelToDomain(it)
        }
        Log.d("user get session after map", user.toString())
        return user
    }

    suspend fun logout() = userPreferences.logout()
}