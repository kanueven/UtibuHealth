package com.rae.utibuhealth.data.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.Gson


@Entity(tableName = "carts")
    data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)  val id: Long = 0,
    @NonNull val itemsJson: String, // Store items as JSON string
    val totalPrice: Double
)

