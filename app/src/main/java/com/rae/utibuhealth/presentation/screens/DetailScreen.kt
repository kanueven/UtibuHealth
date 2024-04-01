package com.rae.utibuhealth.presentation.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rae.utibuhealth.R
import com.rae.utibuhealth.domain.model.Medication
import com.rae.utibuhealth.ui.theme.Primary

@Composable
fun DetailsScreen(medicine: Medication) {
    var quantity by remember{ mutableStateOf(1) } // Initialize quantity with 1

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.hivpill),
                contentDescription = "hiv pill",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = medicine.medicineName,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "$${medicine.price}",
                style = MaterialTheme.typography.bodyLarge,
                color = Primary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Quantity: $quantity",
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = { if (quantity > 1) quantity-- },
                    enabled = quantity > 1
                ) {
                    Text(text = "-")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = { quantity++ }
                ) {
                    Text(text = "+")
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {  }
            ) {
                Text(text = stringResource(id = R.string.add_cart))
            }
        }
    }
}
