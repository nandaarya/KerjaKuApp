package com.example.kerjakuapp.utils

//import android.content.Context
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.kerjakuapp.data.Repository
////import com.example.kerjakuapp.di.Injection
//import com.example.kerjakuapp.ui.home.ui.home.HomeViewModel
//import com.example.kerjakuapp.ui.home.ui.profile.ProfileViewModel
//import com.example.kerjakuapp.ui.home.ui.services.ServicesViewModel
//import com.example.kerjakuapp.ui.login.LoginViewModel
//import com.example.kerjakuapp.ui.main.MainViewModel
//import com.example.kerjakuapp.ui.signup.SignupViewModel
//
//class ViewModelFactory(private val repository: Repository) : ViewModelProvider.NewInstanceFactory() {
//
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        return when {
//            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
//                MainViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
//                LoginViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(SignupViewModel::class.java) -> {
//                SignupViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
//                HomeViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
//                ProfileViewModel(repository) as T
//            }
//            modelClass.isAssignableFrom(ServicesViewModel::class.java) -> {
//                ServicesViewModel(repository) as T
//            }
//            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var INSTANCE: ViewModelFactory? = null
//        @JvmStatic
//        fun getInstance(context: Context): ViewModelFactory {
//            if (INSTANCE == null) {
//                synchronized(ViewModelFactory::class.java) {
//                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
//                }
//            }
//            return INSTANCE as ViewModelFactory
//        }
//    }
//}