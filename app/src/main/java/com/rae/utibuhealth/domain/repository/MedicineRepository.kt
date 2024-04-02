package com.rae.utibuhealth.domain.repository

import android.net.Network
import com.rae.utibuhealth.domain.model.Medicine
import com.rae.utibuhealth.util.NetworkResult

interface MedicineRepository {
    suspend fun getMedicines(): NetworkResult<List<Medicine>>
    suspend fun searchMedicines(query:String): NetworkResult<List<Medicine>>
    suspend fun getMedicineDetails(medicineId: Int): NetworkResult<Medicine>
}