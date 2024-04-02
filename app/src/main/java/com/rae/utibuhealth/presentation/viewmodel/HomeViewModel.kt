package com.rae.utibuhealth.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rae.utibuhealth.domain.model.Medicine
import com.rae.utibuhealth.domain.repository.MedicineRepository
import com.rae.utibuhealth.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: MedicineRepository
): ViewModel() {
    //live data for list of medications
    private val _medicineList = MutableLiveData<NetworkResult<List<Medicine>>>()
    val medicineList: LiveData<NetworkResult<List<Medicine>>> = _medicineList


    // UI state to represent loading, success, or error for medicine details
    private val _medicineDetailsUiState = MutableLiveData<NetworkResult<Medicine?>>()
    val medicineDetailsUiState: LiveData<NetworkResult<Medicine?>> = _medicineDetailsUiState

    // Search query entered by the user
    private val _searchQuery = MutableLiveData<String>()
    val searchQuery: LiveData<String> = _searchQuery

    // Selected medicine for detailed information display
    private val _selectedMedicine = MutableLiveData<Medicine?>(null)
    val selectedMedicine: LiveData<Medicine?> = _selectedMedicine

    // Function to handle quantity changes (triggered from MedicineScreen)
    private val _onQuantityChange = MutableLiveData<Pair<Int, Medicine>?>(null) // Can be null


    init {
        fetchMedicines()
    }


    //function to fetch medicine from a repository
    private fun fetchMedicines() {
        viewModelScope.launch {
            val result = repository.getMedicines()
            when (result) {
                is NetworkResult.Loading -> {
                    _medicineList.value = NetworkResult.Loading

                }

                is NetworkResult.Success -> {
                    _medicineList.value = NetworkResult.Success<List<Medicine>>(result.data)
                }

                is NetworkResult.Error -> {
                    _medicineList.value = NetworkResult.Error(result.exception)
                }
            }
        }
    }

    // function to handle search queries
    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        fetchMedicines()
    }

    // Handle when a medicine item is clicked
    //ts main purpose is to initiate the process of fetching detailed information about the clicked medicine
    //then it asynchronously fetches the detailed information about the medicine using the repository.getMedicineDetails function
    //Once the detailed information is obtained, it updates the UI state with the result and sets the selected medicine LiveData to the fetched detailed medicine.
    fun onMedicineClicked(medicine: Medicine) {
        // Load detailed information for the selected medicine
        viewModelScope.launch {
            _medicineDetailsUiState.value = NetworkResult.Loading
            val detailedMedicineResult = repository.getMedicineDetails(medicine.id.toInt())
            _medicineDetailsUiState.value = detailedMedicineResult
            when (detailedMedicineResult) {
                is NetworkResult.Success -> {
                    _selectedMedicine.value = detailedMedicineResult.data
                }

                is NetworkResult.Error-> {
                    _medicineDetailsUiState.value = NetworkResult.Error(
                        Exception("Error fetching medicine details: ${detailedMedicineResult.exception.message}")
                    )

                }else ->{

                }
            }
        }
    }

    //his function is a helper function designed to specifically fetch detailed information about a medicine using its ID
    //It performs a similar operation to onMedicineClicked in terms of fetching detailed information, but it does not handle UI interaction directly.
    // Instead, it's a reusable function that can be called from other parts of the ViewModel logic.
    fun fetchMedicineDetails(medicineId: Int) {
        viewModelScope.launch {
            _medicineDetailsUiState.value = NetworkResult.Loading
            val detailedMedicineResult = repository.getMedicineDetails(medicineId)
            _medicineDetailsUiState.value = detailedMedicineResult
            when (detailedMedicineResult) {
                is NetworkResult.Success -> {
                    _selectedMedicine.value = detailedMedicineResult.data
                }

                else -> {
                    // Handle error or loading state if needed
                }
            }
        }
    }

    fun onQuantityChange(medicineId: Int?, newQuantity: Int) {
        val selectedMedicine = _selectedMedicine.value
        if (selectedMedicine != null && medicineId != null) {
            // Both medicine and medicineId are guaranteed to be non-null here
            val medicineWithQuantity = Pair(selectedMedicine, newQuantity)
         //   _onQuantityChange.value = medicineWithQuantity
            // Consider updating quantity in the repository based on medicineWithQuantity
        } else {
            // Handle cases where selectedMedicine or medicineId is null
            // (e.g., show error message, disable quantity update button)
        }
    }
}


