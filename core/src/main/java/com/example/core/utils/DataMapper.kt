package com.example.core.utils

import com.example.core.data.local.model.UserModel
import com.example.core.data.remote.response.LoginResult
import com.example.core.domain.model.User

object DataMapper {
    fun mapResponseToDomain(input: LoginResult): User =
        User(
            userId = input.userId,
            name = input.name,
            role = input.role,
            token = input.token,
            isLoggedIn = false
        )

    fun mapDataStoreModelToDomain(input: UserModel): User =
        User(
            userId = input.userId,
            name = input.name,
            role = input.role,
            token = input.token,
            isLoggedIn = false
        )

    //
    fun mapDomainToDataStoreModel(input: User) = UserModel(
        userId = input.userId,
        name = input.name,
        role = input.role,
        token = input.token,
        isLoggedIn = input.isLoggedIn
    )
}