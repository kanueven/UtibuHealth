package com.rae.utibuhealth.presentation.screens.medicine

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rae.utibuhealth.R
import com.rae.utibuhealth.domain.model.Medicine
import com.rae.utibuhealth.presentation.viewmodel.HomeViewModel
import com.rae.utibuhealth.util.NetworkResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicineScreen(
    medicineId: Int,// Allow medicine to be nullable
    viewModel: HomeViewModel, // Use the HomeViewModel for medicine details
    onAddToCart: (Medicine) -> Unit,
    navController: NavController
) {

    var medicine by remember { mutableStateOf<Medicine?>(null) }

    val listState = rememberLazyListState()

    LaunchedEffect(key1 = medicineId) {
        viewModel.fetchMedicineDetails(medicineId)

    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(medicine?.name ?: "") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("action_medicineScreen_to_homeScreen") }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val result = viewModel.medicineDetailsUiState.value) {
                is NetworkResult.Loading -> {
                    // Show loading indicator
                    Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                is NetworkResult.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        state = listState,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            // Show medicine details
                            MedicineInfo(medicine = medicine)
                            Spacer(modifier = Modifier.height(16.dp))
                            QuantityCalculator(
                                medicineId = medicineId,
                                onQuantityChange = { newQuantity ->
                                    viewModel.onQuantityChange(
                                        newQuantity,
                                        medicineId
                                    )
                                },
                                viewModel = viewModel
                            ) // Pass medicine ID
                            Spacer(modifier = Modifier.height(16.dp))
                            //  StockHandle(medicineData)
                            Spacer(modifier = Modifier.height(16.dp))
                           // ButtonUse(medicine=medicine) // Pass medicine object directly
                        }
                    }
                }

                is NetworkResult.Error -> {
                    // Show error message

                    Text(
                        text = stringResource(id = R.string.error_message),
                        modifier = Modifier.padding(16.dp),
                        color = Color.Red
                    )
                }

                else -> {}
            }
        }
    }
}

@Composable
fun MedicineInfo(medicine: Medicine?) {
    if (medicine != null) {
        Column {


            Image(
                painter = painterResource(id = R.drawable.hivpill),
                contentDescription = "medicine image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .align(Alignment.CenterHorizontally)
                    .padding(vertical = 16.dp),
                contentScale = ContentScale.FillBounds
            )
            Text(
                text = "Price: $ ${medicine.price}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onPrimary
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Description: ${medicine.description}",
                style = MaterialTheme.typography.titleMedium,
                maxLines = 5,
                overflow = TextOverflow.Ellipsis
            )
        }
    } else {
        Text("Medicine details not available.")
    }
}


@Composable
fun QuantityCalculator(
    medicineId: Int,
    onQuantityChange: (Int) -> Unit,
    viewModel: HomeViewModel
) {
    var currentQuantity by remember { mutableIntStateOf(1) } // Initial quantity

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(text = stringResource(R.string.quantity))
        Spacer(modifier = Modifier.width(8.dp))
        QuantityButton(
            text = "-",
            onClick = { medicineId -> // Access medicineId from the argument
                if (currentQuantity > 1) currentQuantity--
                viewModel.onQuantityChange(medicineId, currentQuantity)
            },

            )
        Spacer(modifier = Modifier.width(8.dp))
    }
}

@Composable
fun QuantityButton(
    text: String, onClick: (medicineId: Int) -> Unit,
) {
    Button(
        onClick = {},
        modifier = Modifier.size(36.dp),
        colors = ButtonDefaults.buttonColors(
            Color.LightGray,
            contentColor = Color.Black
        ),
        shape = CircleShape
    ) {
        Text(text = text)
    }
}

//@Composable
//fun ButtonUse() {
//    Button(
//        onClick = { /* Handle button click */ },
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 16.dp)
//    ) {
//        Text(text = stringResource(id = R.string.add_cart))
//    }
//}
