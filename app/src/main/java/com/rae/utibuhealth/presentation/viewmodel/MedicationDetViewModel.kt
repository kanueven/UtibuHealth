package com.rae.utibuhealth.presentation.viewmodel

import MedicineDao
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rae.utibuhealth.data.AppDatabase


import com.rae.utibuhealth.data.model.Medication
import kotlinx.coroutines.launch

class MedicationDetViewModel(
    private val medicineDao: MedicineDao = AppDatabase.getDatabase().medicineDao()
) : ViewModel() {

    fun insertMedications(medications: List<Medication>) {
        viewModelScope.launch {
            medicineDao.insertMedications(medications)
        }
    }
}
