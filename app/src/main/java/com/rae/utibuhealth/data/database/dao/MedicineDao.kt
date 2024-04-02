package com.rae.utibuhealth.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rae.utibuhealth.domain.model.Medicine

@Dao
interface MedicineDao {


        @Insert(onConflict = OnConflictStrategy.REPLACE)
        suspend fun insertMedicines(medicines: List<Medicine>)

        @Query("SELECT * FROM medicines")
        suspend fun getMedicines(): List<Medicine>

        @Query("SELECT * FROM medicines WHERE name LIKE '%' || :query || '%'")
        suspend fun searchMedicines(query: String): List<Medicine>
    }
