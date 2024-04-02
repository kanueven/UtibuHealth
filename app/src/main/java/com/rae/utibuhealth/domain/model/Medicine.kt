package com.rae.utibuhealth.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class Medicine(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    val price: Double,
    val description: String,
    val imageUrl: String? = null,
    val isInStock: Boolean,
    val medicineId: String
)

