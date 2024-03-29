import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rae.utibuhealth.data.model.Medication

@Dao
interface MedicineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedications(medication: Medication)

    @Query("SELECT * FROM medicine")
    fun getAllMedicines(): LiveData<List<Medication>>
}
