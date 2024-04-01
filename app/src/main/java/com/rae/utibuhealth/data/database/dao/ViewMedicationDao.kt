package com.rae.utibuhealth.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rae.utibuhealth.data.model.ViewedMedication

@Dao
interface ViewMedicationDao {


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insert(viewedMedication: ViewedMedication)

        @Query("SELECT * FROM viewed_medications")
        suspend fun getAllViewedMedications(): List<ViewedMedication>

        @Delete
        suspend fun deleteViewedMedication(medicationId: Int)
    }

    class ViewedMedicationDaoImpl(private val db: CartDatabase) : ViewedMedicationDao {

        // ... (implement methods using Room database queries)
    }
}