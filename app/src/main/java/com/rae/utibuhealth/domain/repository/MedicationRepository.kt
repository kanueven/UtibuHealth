package com.rae.utibuhealth.domain.repository

import com.rae.utibuhealth.domain.model.Medication
import com.rae.utibuhealth.util.Resource

interface MedicationRepository {
    suspend fun fetchMedications (): Resource<List<Medication>>
}