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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rae.utibuhealth.R
import com.rae.utibuhealth.data.repository.CartRepositoryImpl

import com.rae.utibuhealth.domain.model.Medicine
import com.rae.utibuhealth.presentation.components.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rae.utibuhealth.presentation.viewmodel.CartViewModel
import com.rae.utibuhealth.ui.theme.Primary
import com.rae.utibuhealth.ui.theme.UtibuHealthTheme

@Composable
fun MyCart(
    viewmodel: CartViewModel = viewModel(),
    onMedicineClick: (Medicine) -> Unit = {},
    onBuyNowClick: () -> Unit = {},
) {
    val cartState by viewmodel.cartLiveData.observeAsState()
    Column {
        TopAppBar(title = stringResource(id = R.string.my_cart),
            icon = Icons.Filled.ArrowBack ) {

        }

        if (cartState?.isEmpty()==true) {
            Text(
                text = stringResource(id = R.string.cart_empty),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 16.dp),
                textAlign = TextAlign.Center
            )
        } else {
            CartItemList(cartState?.items ?: emptyList(), onMedicineClick)
            Spacer(modifier = Modifier.height(16.dp))
            TotalPrice(cartState?.items ?: emptyList())
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
    cartItems: List<Medicine>,
    onMedicineClick: (Medicine) -> Unit,
) {
    Column {
        cartItems.forEach { medicine ->
            CartItem(medicine, onMedicineClick) // Pass the individual medicine
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
fun CartItem(
    medicine: Medicine,
    onMedicineClick: (Medicine) -> Unit,

    ) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { onMedicineClick(medicine) }) {
            Image(
                painter = painterResource(id = medicine.imageUrl?.toInt() ?: 0),
                contentDescription = "image",
                modifier = Modifier.size(50.dp)
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = medicine.name,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(text = "$${medicine.price}", color = Primary)
        }

    }
}

@Composable
fun TotalPrice(cartItem: List<Medicine>) {
    var totalPrice = 0.0
    cartItem.forEach { medicine -> totalPrice += medicine.price }
    val formattedTotalPrice = "%.2f".format(totalPrice) // Format with 2 decimal places
    Text(
        text = "Total Price: $$formattedTotalPrice",
        style = MaterialTheme.typography.displayMedium,
        color = Primary
    )
}

@Preview
@Composable
fun MyCartPreviw(){
    UtibuHealthTheme {
        MyCart()
    }
}