package com.rae.utibuhealth.api

import com.rae.utibuhealth.data.model.LoginRequest
import com.rae.utibuhealth.data.model.LoginResponse
import com.rae.utibuhealth.data.model.MedicationListResponse
import com.rae.utibuhealth.data.model.RegisterRequest
import com.rae.utibuhealth.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("/api/medicine")
    suspend fun getAllMedicines(): MedicationListResponse

    @POST("/api/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/api/register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse
}