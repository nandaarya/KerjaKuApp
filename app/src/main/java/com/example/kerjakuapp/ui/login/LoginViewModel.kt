package com.example.kerjakuapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.kerjakuapp.data.Repository
import com.example.kerjakuapp.data.UserModel
import com.example.kerjakuapp.data.response.LoginResponse
import com.example.kerjakuapp.data.Result

class LoginViewModel(private val repository: Repository) : ViewModel() {

    private val _loginResponse = MediatorLiveData<Result<LoginResponse>>()
    val loginResponse: LiveData<Result<LoginResponse>> = _loginResponse

    fun login(email: String, password: String) {
        val liveData = repository.login(email, password)
        _loginResponse.addSource(liveData) { result ->
            _loginResponse.value = result
        }
    }

    fun getSession(): LiveData<UserModel> {
        return repository.getSession().asLiveData()
    }
}