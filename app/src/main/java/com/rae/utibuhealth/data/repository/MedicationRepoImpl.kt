package com.rae.utibuhealth.data.repository

import com.rae.utibuhealth.api.ApiService
import com.rae.utibuhealth.domain.model.Medication
import com.rae.utibuhealth.domain.repository.MedicationRepository
import com.rae.utibuhealth.util.Resource

class MedicationRepoImpl(
    private val apiService :ApiService
) : MedicationRepository {
    override suspend fun fetchMedications(): Resource<List<Medication>> {
        return try {
            val medicationListResponse = apiService.getAllMedicines()
            val medications = medicationListResponse.medications
            Resource.Success(medications)
        } catch (e: Exception) {
            Resource.Error("Failed to fetch medication data: ${e.message}")
        }
    }
}