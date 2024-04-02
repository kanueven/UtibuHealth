package com.rae.utibuhealth.data.repository

import com.rae.utibuhealth.api.ApiService
import com.rae.utibuhealth.data.database.dao.MedicineDao

import com.rae.utibuhealth.domain.model.Medicine
import com.rae.utibuhealth.domain.repository.MedicineRepository
import com.rae.utibuhealth.util.NetworkResult
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class MedicineRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : MedicineRepository {

    override suspend fun getMedicines(): NetworkResult<List<Medicine>> {
        return flow {
            emit(NetworkResult.Loading)
            val response = try {
                apiService.getMedicines()
            } catch (e: Exception) {
                throw handleException(e)
            }
            emit(NetworkResult.Success(response))
        }.first()
    }

    override suspend fun searchMedicines(query: String): NetworkResult<List<Medicine>> {
        return flow {
            emit(NetworkResult.Loading)
            val response = try {
                apiService.searchMedicines(query)
            } catch (e: Exception) {
                throw handleException(e)
            }
            emit(NetworkResult.Success(response))
        }.first()
    }


    override suspend fun getMedicineDetails(medicineId: Int): NetworkResult<Medicine> {
        return try {
            val response = apiService.getMedicineDetails(medicineId)
            NetworkResult.Success(response) // Response is directly the Medicine object
        } catch (e: Exception) {
            NetworkResult.Error(e)
        }
    }


    private fun handleException(e: Exception): Exception {

        return Exception("Error fetching medicines: ${e.message}")
    }
}