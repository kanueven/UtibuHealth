package com.rae.utibuhealth.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "viewed_medications")
data class ViewedMedication(
    @PrimaryKey val medicationId: Int
)
