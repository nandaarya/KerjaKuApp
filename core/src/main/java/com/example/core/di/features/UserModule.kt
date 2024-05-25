package com.example.core.di.features

import com.example.core.data.repository.UserRepository
import com.example.core.di.DataStoreModule
import com.example.core.di.NetworkModule
import com.example.core.domain.repository.IUserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [NetworkModule::class, DataStoreModule::class])
abstract class UserModule {
    @Binds
    abstract fun provideUserRepository(userRepository: UserRepository): IUserRepository
}