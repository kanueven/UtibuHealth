package com.rae.utibuhealth.di

import com.google.gson.GsonBuilder
import com.rae.utibuhealth.api.ApiService
import com.rae.utibuhealth.data.model.MedicationListResponse
import com.rae.utibuhealth.data.model.Medication
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {

    private const val BASE_URL = "https://your-api.com/"  // Replace with your actual base URL

    private val gson = GsonBuilder().create()

    private fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

        return builder
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    val apiService: ApiService = retrofit.create(ApiService::class.java)




}
