package com.rae.utibuhealth.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rae.utibuhealth.data.model.Cart
import com.rae.utibuhealth.data.model.CartItemEntity

@Dao
interface CartDao {

        @Query("SELECT * FROM carts LIMIT 1")
        suspend fun getCart(): Cart?

        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertCart(cart: CartItemEntity)

        @Delete
                suspend fun clearCart()
}
