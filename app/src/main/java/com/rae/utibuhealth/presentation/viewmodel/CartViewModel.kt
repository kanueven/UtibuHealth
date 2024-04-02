package com.rae.utibuhealth.presentation.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.rae.utibuhealth.data.database.dao.CartDao
import com.rae.utibuhealth.data.model.CartItemEntity
import com.rae.utibuhealth.domain.model.Medicine
import com.rae.utibuhealth.domain.repository.CartRepository
import kotlinx.coroutines.launch


import androidx.lifecycle.ViewModel
import com.rae.utibuhealth.data.model.Cart
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val cartRepository: CartRepository
) : ViewModel() {

    private val _cartLiveData = MutableLiveData<Cart?>()
    val cartLiveData: LiveData<Cart?> = _cartLiveData

    fun addToCart(medicine: Medicine, quantity: Int) {
        viewModelScope.launch {
            cartRepository.addToCart(medicine, quantity)
            refreshCart()
        }
    }

    fun getCartItems() {
        viewModelScope.launch {
            _cartLiveData.value = cartRepository.getCartItems()
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            cartRepository.clearCart()
            _cartLiveData.value = null // Update LiveData after clearing cart
        }
    }

    private suspend fun refreshCart() {
        _cartLiveData.value = cartRepository.getCartItems()
    }
}