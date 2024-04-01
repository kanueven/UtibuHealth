package com.rae.utibuhealth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rae.utibuhealth.domain.model.Medication
import com.rae.utibuhealth.presentation.screens.DetailsScreen
import com.rae.utibuhealth.ui.theme.UtibuHealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UtibuHealthTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   DetailsScreen(medicine =
                   Medication(
                       medicineName = "Hiv pills",
                       price = 2.00,
                       image = "R.drawable.hivpill",
                       description = "This is a drug that helps with headches,njhhsdihfihfafagaf",
                       isInStock = true
                   )
                   )

                }
            }
        }
    }
}
@Preview
@Composable
fun DetPreview(){
    DetailsScreen(medicine =
    Medication(
        medicineName = "Hiv pills",
        price = 2.00,
        image = "R.drawable.hivpill",
        description = "This is a drug that helps with headches,njhhsdihfihfafagaf",
        isInStock = true
    )
    )
}

