package com.rae.utibuhealth.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName= "medicine")
data class Medication(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val medicineName: String,
    val price: Float,
    val description: String,
    val image: String,
    val isInStock: Boolean // Indicates whether the medicine is in stock or not
)
