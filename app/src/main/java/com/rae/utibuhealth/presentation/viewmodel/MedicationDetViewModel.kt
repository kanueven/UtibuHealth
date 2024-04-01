package com.rae.utibuhealth.presentation.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rae.utibuhealth.api.ApiService


import com.rae.utibuhealth.domain.model.Medication
import kotlinx.coroutines.launch

class MedicationDetViewModel(
    private val medicationId: Int,
    private val viewedMedicationDao: ViewedMedicationDao,
    private apiService: ApiService

) : ViewModel() {
    private val _medication = MutableLiveData<Medication?>(null)
    val medication: LiveData<Medication?> = _medication

    init {
        viewModelScope.launch {
            val medicine = //fetch medication details using medicatinId
                _medication.value
            // Persist viewed medication ID
            viewedMedicationDao.insert(ViewedMedication(medicationId))
            // Synchronize with server
            cartService.addViewedMedication(getUserId(), medicationId)
        }
    }

    fun onQuantityChange(newQuantity: Int){

    }

}
