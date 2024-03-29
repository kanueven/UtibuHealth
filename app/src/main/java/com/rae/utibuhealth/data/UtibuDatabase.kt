package com.rae.utibuhealth.data

import MedicineDao
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rae.utibuhealth.data.model.Medication

@Database(entities = [Medication::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun medicineDao(): MedicineDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "utibuhealth_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
