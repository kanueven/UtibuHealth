package com.rae.utibuhealth.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rae.utibuhealth.R
import com.rae.utibuhealth.domain.model.Medication
import com.rae.utibuhealth.presentation.components.ButtonComponent
import com.rae.utibuhealth.presentation.components.TopAppBar
import com.rae.utibuhealth.presentation.viewmodel.MedicationDetViewModel
import com.rae.utibuhealth.ui.theme.Primary
import com.rae.utibuhealth.ui.theme.Secondary
import com.rae.utibuhealth.ui.theme.UtibuHealthTheme

@Composable
fun MedicineScreen(
    medicationId: Int, // Pass medication ID to fetch details
    onAddToCart: (Medication) -> Unit = {}
) {
    val viewModel: MedicationDetViewModel = composeViewModel(factory= {MedicationDetViewModel(medicationId)})
    val medication by viewModel.medication.collectAsState()
    Column {
        TopAppBar(
            // Handle potential null medication
            title = medication?.medicineName?:"",
            icon = Icons.Filled.ArrowBack,
            onIconClick = {}
        )
        Column {

            if (medication != null) { // Display content only if medication is available
                Image(
                    painter = medication.image,
                    contentDescription = "hiv pill",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .align(Alignment.CenterHorizontally)
                        .padding(vertical = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                MedicineInfo(medication)
                Spacer(modifier = Modifier.height(16.dp))
                QuantityCalculator(viewModel::onQuantityChange)
                Spacer(modifier = Modifier.height(16.dp))
                StockHandle(medication)
                Spacer(modifier = Modifier.height(16.dp))
                ButtonUse(text = stringResource(id = R.string.add_cart),
                    onClick = {
                        onAddToCart(medication!!)// Pass medication only if not null
                    })
            }else{
                // Show loading indicator or error message if medication details are not yet fetched
                Text("Loading medication details...")
            }
        }
    }
}

@Composable
fun MedicineInfo(medicine: Medication) {
    Column {
        Text(
            text = "Price: $${medicine.price}",
            style = MaterialTheme.typography.bodyLarge,
            color = Primary
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Description: ${medicine.description}",
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
fun QuantityCalculator(onQuantityChange: (Int) -> Unit) {
    var currentQuantity by remember { mutableStateOf(1) }  // Initial quantity
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = stringResource(id = R.string.quantity))
        Spacer(modifier = Modifier.width(8.dp))
        QuantityButton(text = "-", onClick = { if (currentQuantity > 1) currentQuantity-- }) {
            onQuantityChange(currentQuantity) // Update quantity in ViewModel on decrement
        }
        Spacer(modifier = Modifier.width(8.dp))
        QuantityButton(text = "+", onClick = { currentQuantity++ }) {
            onQuantityChange(currentQuantity) // Update quantity in ViewModel on increment
    }
}

@Composable
fun QuantityButton(text: String, onClick: () -> Unit, onUpdate: ()-> Unit) {
    Button(
        onClick = onClick,
        shape = CircleShape,
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        modifier = Modifier.size(48.dp).clickable { onUpdate() }
    ) {
        Text(text = text)
    }
}

@Composable
fun StockHandle(medicine: Medication) {
    var isInStock by remember { mutableStateOf(medicine.isInStock) }

    Text(
        text = if (isInStock) {
            stringResource(id = R.string.in_stock)
        } else {
            stringResource(id = R.string.out_of_stock)
        },
        color = if (isInStock) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
    )
}

@Composable
fun ButtonUse(
    text:String,
    onClick: ()-> Unit
){
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(listOf(Secondary, Primary)),
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center,
        ) {
            Text(text = text)
        }
    }
}
@Preview
@Composable
fun MedScrePreview(){
    UtibuHealthTheme {
        MedicineScreen(medicine =
        Medication(
            medicineId = 1,
            medicineName = "Hivpill",
            image = "R.drawable.hivpill",
            description = "kjdfgtrdfytrdfghuuyttrdesdfgytfd",
            isInStock = true,
            price = 20.00

        ))
    }
}