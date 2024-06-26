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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.rae.utibuhealth.presentation.screens.HomeScreen
import com.rae.utibuhealth.presentation.screens.medicine.MedicineScreen

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
                    val navController = rememberNavController()

                    NavHost(navController, startDestination = "homeScreen") {
                        composable("homeScreen") { HomeScreen(navController) }
                        composable("medicineDetailScreen") { MedicineScreen(navController) }

                }
            }
        }
    }
}
}
