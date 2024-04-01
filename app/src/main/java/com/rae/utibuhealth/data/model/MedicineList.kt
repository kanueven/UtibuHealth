package com.rae.utibuhealth.data.model

import com.rae.utibuhealth.domain.model.Medication


data class MedicationListResponse(
    val medications: List<Medication>
)
