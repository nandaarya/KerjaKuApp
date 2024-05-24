package com.example.profile.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.core.domain.model.User
import com.example.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val userUseCase: UserUseCase): ViewModel() {

    fun getSession(): LiveData<User> = userUseCase.getSession().asLiveData()

    fun logout() {
        viewModelScope.launch {
            userUseCase.logout()
        }
    }
}