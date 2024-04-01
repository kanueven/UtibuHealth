package com.rae.utibuhealth.domain.repository

import com.rae.utibuhealth.domain.model.Medication
import com.rae.utibuhealth.presentation.viewmodel.CartItem

interface CartRepository {

    suspend fun addToCart(userId: String, medicine: Medication, quantity: Int = 1):Boolean
    suspend fun getCartItems(userId: String): List<CartItem>

    suspend fun clearCart(userId: String)
}