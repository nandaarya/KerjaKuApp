package com.example.kerjakuapp.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.core.domain.model.User
import com.example.core.domain.usecase.user.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val userUseCase: UserUseCase) : ViewModel() {

    fun getSession(): LiveData<User> = userUseCase.getSession().asLiveData()

}