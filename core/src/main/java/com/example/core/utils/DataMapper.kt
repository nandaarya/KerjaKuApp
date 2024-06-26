package com.example.core.utils

import com.example.core.data.local.model.UserModel
import com.example.core.data.remote.response.admin.EmployeeLeaveReviewResult
import com.example.core.data.remote.response.admin.EmployeeSickLeaveResult
import com.example.core.data.remote.response.admin.FindEmployeeByEmployeeNameResult
import com.example.core.data.remote.response.admin.ReimbursementReviewResult
import com.example.core.data.remote.response.user.DataAttendanceResult
import com.example.core.data.remote.response.user.LoginResult
import com.example.core.domain.model.admin.EmployeeLeaveReview
import com.example.core.domain.model.admin.EmployeeSickLeave
import com.example.core.domain.model.admin.FindEmployeeByEmployeeName
import com.example.core.domain.model.admin.ReimbursementReview
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

    fun mapEmployeeSickLeaveResponseToDomain(input: EmployeeSickLeaveResult): EmployeeSickLeave =
        EmployeeSickLeave(
            idIzinSakit = input.idIzinSakit,
            idPegawai = input.idPegawai,
            tanggalPengajuan = input.tanggalPengajuan,
            tanggalMulai = input.tanggalMulai,
            tanggalSelesai = input.tanggalSelesai,
            keteranganSakit = input.keteranganSakit,
            bukti = input.bukti,
            statusPengajuan = input.statusPengajuan,
            tanggalPersetujuan = input.tanggalPersetujuan,
            pesanPersetujuan = input.pesanPersetujuan
        )

    fun mapReimbursementReviewResponseToDomain(input: ReimbursementReviewResult): ReimbursementReview =
        ReimbursementReview(
            idReimbursement = input.idReimbursement,
            idPegawai = input.idPegawai,
            tanggalPengajuan = input.tanggalPengajuan,
            jenisReimbursement = input.jenisReimbursement,
            deskripsi = input.deskripsi,
            jumlahPengeluaran = input.jumlahPengeluaran,
            bukti = input.bukti,
            statusPengajuan = input.statusPengajuan,
            tanggalPersetujuan = input.tanggalPersetujuan
        )

    fun mapFindEmployeeByEmployeeNameResponseToDomain(input: FindEmployeeByEmployeeNameResult): FindEmployeeByEmployeeName =
        FindEmployeeByEmployeeName(
            employeeId = input.employeeId,
            employeeName = input.employeeName,
            department = input.department
        )
}