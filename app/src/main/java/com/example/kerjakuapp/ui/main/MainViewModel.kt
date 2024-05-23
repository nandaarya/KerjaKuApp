package com.example.kerjakuapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.User
import com.example.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

//    private var _userRole = MediatorLiveData<String>()
//    var userRole: LiveData<String> = _userRole
//
//    fun setUserRole(userRole: String) {
//        _userRole.value = userRole
//    }
//
//    private fun getToken(): String {
//        var token = ""
//        viewModelScope.launch {
//            repository.getSession().collect{ user ->
//                token = user.token
//            }
//        }
//        return token
//    }

    fun getSession(): LiveData<User> = userUseCase.getSession().asLiveData()

//    fun logout() {
//        viewModelScope.launch {
//            repository.logout()
//        }
//    }
}