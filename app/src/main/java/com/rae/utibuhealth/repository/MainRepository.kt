package com.rae.utibuhealth.repository

import com.rae.utibuhealth.api.ApiService
import com.rae.utibuhealth.data.model.LoginRequest
import com.rae.utibuhealth.data.database.model.MedicineList
import com.rae.utibuhealth.data.model.RegisterRequest
import com.rae.utibuhealth.util.Resource


interface MainRepository{
    suspend fun getMedicines(): MedicineList

    suspend fun login(loginRequest: LoginRequest): Resource<LoginRequest>
    suspend fun signup(registerRequest: RegisterRequest): Resource<RegisterRequest>
    //




}
