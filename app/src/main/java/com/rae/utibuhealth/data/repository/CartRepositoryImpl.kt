package com.rae.utibuhealth.data.repository

import com.google.gson.Gson
import com.rae.utibuhealth.api.ApiService
import com.rae.utibuhealth.data.database.dao.CartDao
import com.rae.utibuhealth.data.model.Cart
import com.rae.utibuhealth.data.model.CartItem
import com.rae.utibuhealth.data.model.CartItemEntity
import com.rae.utibuhealth.domain.model.Medicine
import com.rae.utibuhealth.domain.repository.CartRepository

import javax.inject.Inject

class CartRepositoryImpl @Inject constructor(
    private val cartDao: CartDao,
    private val cartService: ApiService // Inject CartService implementation
) : CartRepository {

    override suspend fun addToCart(medicine: Medicine, quantity: Int) {
        val currentCart = getCartItems() ?: Cart(0, emptyList(), 0.0)
        val updatedItems = updateCartItems(currentCart.items, medicine, quantity)
        val newTotalPrice = currentCart.totalPrice + (medicine.price * quantity)
        val updatedCart = Cart(currentCart.id, updatedItems, newTotalPrice)
        insertCart(updatedCart)
    }

    override suspend fun getCartItems(): Cart? {
        val cartEntity = cartDao.getCart()
        return cartEntity?.cartEntityToCart()
            ?: Cart(0, emptyList(), 0.0) // Convert CartEntity to Cart object
    }

    override suspend fun clearCart() {
        cartDao.clearCart()
    }

    override suspend fun updateCartItems(
        currentItems: List<CartItem>,
        medicine: Medicine,
        quantity: Int
    ): List<CartItem> {
        val existingItem = currentItems.find { it.medicine.id == medicine.id }
        return if (existingItem != null) {
            val updatedQuantity = existingItem.quantity + quantity
            currentItems.map { if (it.medicine.id == medicine.id) CartItem(medicine, updatedQuantity) else it }
        } else {
            currentItems.plus(CartItem(medicine, quantity))
        }
    }

    override suspend fun insertCart(cart: Cart) {
        cartDao.insertCart(
            CartItemEntity(
                cart.id,
                Gson().toJson(cart.items),
                cart.totalPrice
            )
        )
    }

    private fun CartItemEntity.cartEntityToCart(): Cart {
        val cartItems = Gson().fromJson(itemsJson, Array<CartItem>::class.java).toList()
        return Cart(id, cartItems, totalPrice)
    }
}
