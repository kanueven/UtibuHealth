package com.rae.utibuhealth.repository

import com.rae.utibuhealth.data.model.Medication
import com.rae.utibuhealth.util.Resource

interface MedicationRepository {
    suspend fun fetchMedications (): Resource<List<Medication>>
}