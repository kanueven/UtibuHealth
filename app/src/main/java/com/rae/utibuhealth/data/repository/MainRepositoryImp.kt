package com.rae.utibuhealth.repository

import com.rae.utibuhealth.api.ApiService
import com.rae.utibuhealth.data.model.LoginRequest
import com.rae.utibuhealth.data.model.LoginResponse
import com.rae.utibuhealth.data.model.RegisterRequest
import com.rae.utibuhealth.data.model.RegisterResponse
import com.rae.utibuhealth.domain.repository.MainRepository
import com.rae.utibuhealth.util.Resource

abstract class MainRepositoryImp(private val apiService: ApiService) : MainRepository {

//    override suspend fun login(loginRequest: LoginRequest): Resource<LoginResponse> {
//        return try {
//            val response = apiService.login(loginRequest)
//            Resource.Success(response)
//        } catch (e: Exception) {
//            Resource.Error("Login was unsuccessful: ${e.message}")
//        }
//    }
//
//    override suspend fun signup(registerRequest: RegisterRequest): Resource<RegisterResponse> {
//        return try {
//            val response = apiService.register(registerRequest)
//            Resource.Success(response)
//        } catch (e: Exception) {
//            Resource.Error("Registration was unsuccessful: ${e.message}")
//        }
//    }

}
