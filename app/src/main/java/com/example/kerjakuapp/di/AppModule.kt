package com.example.kerjakuapp.di

import com.example.core.domain.usecase.admin.AdminInteractor
import com.example.core.domain.usecase.admin.AdminUseCase
import com.example.core.domain.usecase.user.UserInteractor
import com.example.core.domain.usecase.user.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideUserUseCase(userInteractor: UserInteractor): UserUseCase

    @Binds
    @Singleton
    abstract fun provideAdminUseCase(adminInteractor: AdminInteractor): AdminUseCase
}