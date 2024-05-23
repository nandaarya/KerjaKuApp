package com.example.kerjakuapp.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.data.remote.network.ApiResponse
import com.example.core.domain.model.User
import com.example.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {
//
//    private val _loginResponse = MediatorLiveData<Result<LoginResponse>>()
//    val loginResponse: LiveData<Result<LoginResponse>> = _loginResponse

    fun login(email: String, password: String): LiveData<ApiResponse<User>> {
        val userLiveData = MutableLiveData<ApiResponse<User>>()

        viewModelScope.launch {
            Log.d("login in view model", "$email, $password")
            userUseCase.login(email, password).collect {
                userLiveData.value = it
            }
        }

        return userLiveData
    }

    fun saveSession(user: User) {
        viewModelScope.launch {
            userUseCase.saveSession(user)
        }
    }

    fun getSession(): LiveData<User> = userUseCase.getSession().asLiveData()
}