    package com.rae.utibuhealth.presentation.screens

    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Arrangement.Absolute.Center
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.height
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.lazy.LazyColumn
    import androidx.compose.foundation.lazy.grid.GridCells
    import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
    import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
    import androidx.compose.foundation.lazy.grid.items
    import androidx.compose.foundation.rememberScrollState
    import androidx.compose.foundation.text.KeyboardOptions
    import androidx.compose.foundation.verticalScroll
    import androidx.compose.material.icons.Icons
    import androidx.compose.material.icons.filled.Home
    import androidx.compose.material.icons.filled.List
    import androidx.compose.material.icons.filled.Person
    import androidx.compose.material.icons.filled.Search
    import androidx.compose.material.icons.filled.ShoppingCart
    import androidx.compose.material3.BottomAppBar
    import androidx.compose.material3.Button
    import androidx.compose.material3.CircularProgressIndicator
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.NavigationBarItem
    import androidx.compose.material3.OutlinedTextField
    import androidx.compose.material3.Scaffold
    import androidx.compose.material3.Surface
    import androidx.compose.material3.Text
    import androidx.compose.material3.TextField
    import androidx.compose.material3.TextFieldDefaults
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.remember
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.res.colorResource
    import androidx.compose.ui.res.stringResource
    import androidx.compose.ui.text.input.KeyboardType
    import androidx.compose.ui.unit.dp
    import androidx.navigation.NavController
    import androidx.navigation.compose.rememberNavController
    import com.rae.utibuhealth.R
    import com.rae.utibuhealth.domain.model.Medicine
    import com.rae.utibuhealth.presentation.components.TopAppBar
    import com.rae.utibuhealth.presentation.viewmodel.HomeViewModel
    import com.rae.utibuhealth.util.NetworkResult


    @Composable
    fun HomeScreen(navController: NavController, viewModel: HomeViewModel) {
        Scaffold(
            topBar = {
                {
                    Text("Utibu Health") }

            },
            bottomBar = { BottomNavSection() },
            content = { innerPadding ->
                // Content of HomeScreen
                Column(modifier = Modifier.padding(innerPadding)) {
                    SearchSection(viewModel = viewModel, navController = navController)
                }
            }
        )
    }

    @Composable
    fun SearchSection(viewModel: HomeViewModel, navController: NavController) {
        val uiState by viewModel.medicineDetailsUiState.observeAsState(NetworkResult.Loading)
        val searchQuery by viewModel.searchQuery.observeAsState("")

        OutlinedTextField(
            value = searchQuery.text, // Extract the text from TextFieldValue
            onValueChange = { viewModel.onSearchQueryChange(it) },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search Medications")
                          },
            maxLines = 1
        )

        when (uiState) {
            is NetworkResult.Loading -> {
                // Show loading indicator
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is NetworkResult.Success -> {
                MedicineList(
                    medicines = (uiState as NetworkResult.Success<List<Medicine>>).data ?: emptyList(),
                    onMedicineClick = { medicine ->
                        viewModel.onMedicineClicked(medicine)
                        navController.navigate("action_homeScreen_to_medicineScreen")
                    }
                )
            }
            is NetworkResult.Error -> {
                // Show error message
                Text(text = "Error: ${(uiState as NetworkResult.Error).exception}")
            }
        }
    }

    @Composable
    fun MedicineList(medicines: List<Medicine>, onMedicineClick: (Medicine) -> Unit) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(medicines) { medicine ->
                GridCard(
                    medicine = medicine,
                    onMedicineClick = onMedicineClick
                )
            }
        }
    }

    @Composable
    fun GridCard(
        medicine: Medicine,
        onMedicineClick: (Medicine) -> Unit,
        modifier: Modifier = Modifier
    ) {
        Surface(
            modifier = modifier.padding(4.dp)
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = medicine.name)
                Button(
                    onClick = { onMedicineClick(medicine) },
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Text(text = "View Details")
                }
            }
        }
    }
    @Composable
    fun BottomNavSection(modifier: Modifier = Modifier) {
        Row(
            modifier = modifier
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            NavigationBarItem(
                icon = {
                    Icon(imageVector = Icons.Default.Home, contentDescription = null)
                },
                label = { Text(text = stringResource(id = R.string.bottom_navigation_home)) },
                selected = true,
                onClick = {}
            )
            NavigationBarItem(
                icon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription = null)
                },
                label = { Text(text = stringResource(id = R.string.bottom_navigation_account)) },
                selected = false,
                onClick = {}
            )
            NavigationBarItem(
                icon = {
                    Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)
                },
                label = { Text(text = stringResource(id = R.string.bottom_navigation_cart)) },
                selected = false,
                onClick = {}
            )
            NavigationBarItem(
                icon = {
                    Icon(imageVector = Icons.Default.List, contentDescription = null)
                },
                label = { Text(text = stringResource(id = R.string.bottom_navigation_order)) },
                selected = false,
                onClick = {}
            )
        }
    }