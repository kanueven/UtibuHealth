package com.rae.utibuhealth.data.model

// representing the overall cart with properties
data class Cart(val id: Long = 0, val items: List<CartItem>, val totalPrice: Double) {
    fun updateTotalPrice(): Double {
        return items.sumByDouble { it.medicine.price * it.quantity }
    }
}