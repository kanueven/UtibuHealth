package com.rae.utibuhealth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rae.utibuhealth.R
import com.rae.utibuhealth.domain.model.Medication
import com.rae.utibuhealth.presentation.components.TopAppBar
import com.rae.utibuhealth.presentation.viewmodel.CartItem
import com.rae.utibuhealth.presentation.viewmodel.CartViewModel
import com.rae.utibuhealth.ui.theme.Primary

@Composable
fun MyCart(
    viewmodel: CartViewModel,
    onMedicineClick: (Medication) -> Unit = {},
    onBuyNowClick: () -> Unit = {},
) {
    Column {
        TopAppBar(title = stringResource(id = R.string.my_cart),
            icon = Icons.Filled.ArrowBack ) {

        }

        if (viewmodel.cartMedicine.isEmpty()) {
            Text(
                text = stringResource(id = R.string.cart_empty),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            CartItemList(viewmodel.cartMedicine, onMedicineClick,
                onRemoveFromCart = {
                    medicine ->
                    viewmodel.removeFromCart(medicine)
                })
            Spacer(modifier = Modifier.height(16.dp))
            TotalPrice(viewmodel.cartMedicine)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = onBuyNowClick,
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(vertical = 16.dp),
                colors = ButtonDefaults.buttonColors(Primary)
            ) {
                Text(text = stringResource(id = R.string.buy_now))
            }
        }
    }
}

@Composable
fun CartItemList(
    cartItem: List<CartItem>,
    onMedicineClick: (Medication) -> Unit,
    onRemoveFromCart: (CartItem) -> Unit,
) {
    Column {
        cartItem.forEach { cartItem ->
            CartItem(cartItem, onMedicineClick,onRemoveFromCart)
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CartItem(
    cartItem: CartItem,
    onMedicineClick: (Medication) -> Unit,
    onRemoveFromCart: (CartItem) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onMedicineClick(cartItem.medicine) }) {
            Image(
                painter = painterResource(id = cartItem.medicine.image),
                contentDescription = cartItem.medicine.medicineName,
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = cartItem.medicine.medicineName,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = "$${cartItem.medicine.price}", color = Primary)
        }
        Text(text = "Qty: ${cartItem.quantity}")
    }
}

@Composable
fun TotalPrice(cartItem: List<CartItem>) {
    var totalPrice = 0.0
    cartItem.forEach { cartItem -> totalPrice += cartItem.medicine.price }
    Text(
        text = "Total Price: $${totalPrice.toString("%.2f")}",  // Format price with 2 decimal places
        style = MaterialTheme.typography.displayMedium,
        color = Primary
    )
}