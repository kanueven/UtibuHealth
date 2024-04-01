package com.rae.utibuhealth.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart_items")
    data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val medicineId: Int,
    val quantity: Int
)
