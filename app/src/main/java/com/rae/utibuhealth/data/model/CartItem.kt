package com.rae.utibuhealth.data.model

import com.rae.utibuhealth.domain.model.Medicine

data class CartItem(val medicine: Medicine, var quantity: Int)
