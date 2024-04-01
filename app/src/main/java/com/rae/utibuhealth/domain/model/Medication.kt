package com.rae.utibuhealth.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey


data class Medication(
    val medicineId: Int,
    val medicineName: String,
    val price: Double,
    val description: String,
    val image: String,
    val isInStock: Boolean // Indicates whether the medicine is in stock or not
)
