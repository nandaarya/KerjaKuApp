package com.example.core.data.local.datastore

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.core.data.local.model.UserModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences  @Inject constructor(private val dataStore: DataStore<Preferences>) {

    suspend fun saveSession(user: UserModel) {
        Log.d("user in userpreference", user.toString())
        dataStore.edit { preferences ->
            Log.d("save session", user.role ?: "role kosong disimpan")
            preferences[USER_ID] = user.userId
            preferences[NAME] = user.name
            preferences[ROLE] = user.role
            preferences[TOKEN_KEY] = user.token
            preferences[IS_LOGIN_KEY] = true
        }
        Log.d("user data store", dataStore.data.toString())
    }

    fun getSession(): Flow<UserModel> {
        return dataStore.data.map { preferences ->
            Log.d("get session", preferences[ROLE] ?: "role kosong")
            UserModel(
                preferences[USER_ID] ?: "",
                preferences[NAME] ?: "",
                preferences[ROLE] ?: "",
                preferences[TOKEN_KEY] ?: "",
                preferences[IS_LOGIN_KEY] ?: false
            )
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private val USER_ID = stringPreferencesKey("userId")
        private val NAME = stringPreferencesKey("name")
        private val ROLE = stringPreferencesKey("role")
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val IS_LOGIN_KEY = booleanPreferencesKey("isLogin")
    }
}