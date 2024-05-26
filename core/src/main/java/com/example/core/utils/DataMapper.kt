package com.example.core.utils

import com.example.core.data.local.model.UserModel
import com.example.core.data.remote.response.admin.EmployeeLeaveReviewResult
import com.example.core.data.remote.response.user.DataAttendanceResult
import com.example.core.data.remote.response.user.LoginResult
import com.example.core.domain.model.admin.EmployeeLeaveReview
import com.example.core.domain.model.user.DataAttendance
import com.example.core.domain.model.user.User

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

    fun mapEmployeeLeaveReviewResponseToDomain(input: EmployeeLeaveReviewResult): EmployeeLeaveReview =
        EmployeeLeaveReview(
            idPengajuanCuti = input.idPengajuanCuti,
            idPegawai = input.idPegawai,
            tanggalPengajuan = input.tanggalPengajuan,
            tanggalMulaiCuti = input.tanggalMulaiCuti,
            tanggalSelesaiCuti = input.tanggalSelesaiCuti,
            jenisCuti = input.jenisCuti,
            keteranganCuti = input.keteranganCuti,
            bukti = input.bukti,
            statusPengajuan = input.statusPengajuan,
            tanggalPersetujuan = input.tanggalPersetujuan,
            pesanPersetujuan = input.pesanPersetujuan
        )
}