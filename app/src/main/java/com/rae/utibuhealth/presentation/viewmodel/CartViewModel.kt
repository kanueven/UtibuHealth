package com.rae.utibuhealth.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.viewModelScope
import com.rae.utibuhealth.data.database.dao.CartDao
import com.rae.utibuhealth.data.model.CartItemEntity
import kotlinx.coroutines.launch

i
import androidx.lifecycle.ViewModel
import com.rae.utibuhealth.domain.model.Medication


class CartViewModel @ViewModelInject constructor(
    private val cartDao: CartDao
):ViewModel() {
    private val _cartMedicine = mutableStateListOf<CartItem>()
    val  cartMedicine: List<CartItem>
        get() = _cartMedicine.toList()
    init {
        viewModelScope.launch {
            _cartMedicine.addAll(cartDao.getAllCartItems().map { CartItem(it.medicineId, it.quantity) })
        }
    }

    //for adding a medication to the cart,
    // handling both adding a new item and updating the quantity of an existing one.
    fun addToCart(medicine: Medication, quantity: Int = 1){
        //find existing item
        val existingItem = _cartMedicine.find { it.medicine.medicineId == medicine.medicineId }
        if (existingItem != null) {
            // increments the existing item's quantity by adding the provided quantity to its current value using
            existingItem.quantity += quantity
        } else {
            //creates a new CartItem

            _cartMedicine.add(CartItem(medicine, quantity))
        }
        viewModelScope.launch {
            cartDao.addToCart(CartItemEntity(0, medicine.medicineId, quantity))
        }


    }
    fun removeFromCart(medicine: CartItem) {
        _cartMedicine.remove(medicine)
        viewModelScope.launch {
            cartDao.removeFromCart(CartItemEntity(medicine.medicineId, medicine.quantity, 0)) // Set ID to 0 (not used for delete)
        }
    }
    fun clearCart(){
        _cartMedicine.clear()
        viewModelScope.launch{
            cartDao.clearCart()
        }

    }

    fun getTotalPrice(): Double{
        return _cartMedicine.sumOf { it.medicine.price * it.quantity}

    }
}
data class CartItem( val medicine:Medication, var quantity: Int)