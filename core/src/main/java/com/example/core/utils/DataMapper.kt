package com.example.core.utils

import com.example.core.data.local.model.UserModel
import com.example.core.data.remote.response.DataAttendanceResult
import com.example.core.data.remote.response.LoginResult
import com.example.core.domain.model.DataAttendance
import com.example.core.domain.model.User

object DataMapper {
    fun mapLoginResponseToDomain(input: LoginResult): User =
        User(
            userId = input.userId,
            name = input.name,
            role = input.role,
            token = input.token,
            isLoggedIn = false
        )

    fun mapDataAttendanceResponseToDomain(input: DataAttendanceResult): DataAttendance =
        DataAttendance(
            idAbsen = input.idAbsen,
            employeeId = input.employeeId,
            time = input.time,
            clockIn = input.clockIn,
            clockOut = input.clockOut
        )

    fun mapDataStoreModelToDomain(input: UserModel): User =
        User(
            userId = input.userId,
            name = input.name,
            role = input.role,
            token = input.token,
            isLoggedIn = input.isLoggedIn
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