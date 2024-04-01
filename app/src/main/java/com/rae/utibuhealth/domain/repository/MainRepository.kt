package com.rae.utibuhealth.domain.repository

import com.rae.utibuhealth.api.ApiService
import com.rae.utibuhealth.data.model.LoginRequest
import com.rae.utibuhealth.data.model.MedicationListResponse
import com.rae.utibuhealth.data.model.RegisterRequest
import com.rae.utibuhealth.util.Resource


interface MainRepository{
    suspend fun getMedicines(): MedicationListResponse

    suspend fun login(loginRequest: LoginRequest): Resource<LoginRequest>
    suspend fun signup(registerRequest: RegisterRequest): Resource<RegisterRequest>
    //




}
