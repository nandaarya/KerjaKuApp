package com.example.profile.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.usecase.UserUseCase
import kotlinx.coroutines.launch

class ProfileViewModel(userUseCase: UserUseCase): ViewModel() {
    fun logout() {
    }
}