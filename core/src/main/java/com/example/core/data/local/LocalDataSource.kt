package com.example.core.data.local

import com.example.core.data.local.datastore.UserPreference
import com.example.core.domain.model.User
import com.example.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val userPreference: UserPreference) {
    suspend fun saveSession(user: User) {
        val userModel = DataMapper.mapDomainToDataStoreModel(user)
        userPreference.saveSession(userModel)
    }

    fun getSession(): Flow<User> {
        val user = userPreference.getSession().map {
            DataMapper.mapDataStoreModelToDomain(it)
        }
        return user
    }

    suspend fun logout() {
        userPreference.logout()
    }
}