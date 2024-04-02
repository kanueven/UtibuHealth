package com.rae.utibuhealth.domain.repository

import com.rae.utibuhealth.data.model.Cart
import com.rae.utibuhealth.data.model.CartItem
import com.rae.utibuhealth.domain.model.Medicine


interface CartRepository {


// fetches the current cart, updates items (considering existing items),
    // calculates the new total price, and inserts the updated cart into Room.
    suspend fun addToCart(medicine: Medicine, quantity: Int )
    //retrieves the cart from Room using getCart
    suspend fun getCartItems(): Cart?

    suspend fun clearCart()

    //handles adding a new item or updating the quantity of an existing item.
    suspend fun updateCartItems(currentItems: List<CartItem>, medicine: Medicine, quantity: Int): List<CartItem>
     suspend fun insertCart(cart: Cart)
}