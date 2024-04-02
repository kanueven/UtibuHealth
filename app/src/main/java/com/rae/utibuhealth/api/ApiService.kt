package com.rae.utibuhealth.api

import com.rae.utibuhealth.data.model.Cart
import com.rae.utibuhealth.data.model.CreateCartRequest
import com.rae.utibuhealth.data.model.LoginRequest
import com.rae.utibuhealth.data.model.LoginResponse

import com.rae.utibuhealth.data.model.RegisterRequest
import com.rae.utibuhealth.data.model.RegisterResponse

import com.rae.utibuhealth.domain.model.Medicine
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {

    @GET("/medications")
    suspend fun getMedicines(): List<Medicine>

    @GET("/medications/search")
    suspend fun searchMedicines(@Path("query")query:String): List<Medicine>

    @GET("/medications/{id}") // Use path parameter for specific ID
    suspend fun getMedicineDetails(@Path("id") id: Int): Medicine  // Specify return type

    // Endpoint to add a viewed medication ID to the user's profile (requires authentication)
    @PUT("/profile/medicines/{medicineId}")  // Use PUT for updates
    suspend fun addViewedMedicine(
        @Path("medicineId") medicineId: Int,
        @Body authToken: String // Assuming authentication token in request body
    ): Unit


    @POST("/carts")
    suspend fun addToCart(@Body cart: Cart): Response<Unit>

    @GET("/carts/{cartId}")
    suspend fun getCartById(@Path ("cartId")cartId:Long): Response<Cart>

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @POST("/register")
    suspend fun register(@Body registerRequest: RegisterRequest): RegisterResponse
}