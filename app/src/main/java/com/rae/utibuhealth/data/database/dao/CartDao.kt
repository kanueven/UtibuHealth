package com.rae.utibuhealth.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rae.utibuhealth.data.model.CartItemEntity

@Dao
interface CartDao {


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun addToCart(cartItem: CartItemEntity)

        @Delete
        suspend fun removeFromCart(cartItem: CartItemEntity)

        @Query("SELECT * FROM cart_items")
        suspend fun getAllCartItems(): List<CartItemEntity>

        @Query("DELETE FROM cart_items")
        suspend fun clearCart()
    }
