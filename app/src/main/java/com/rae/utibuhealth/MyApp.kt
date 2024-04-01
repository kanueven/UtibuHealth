package com.rae.utibuhealth

import android.app.Application
import androidx.room.Room
import com.rae.utibuhealth.data.UtibuAppDatabase


class MyApp : Application() {

        private lateinit var database: UtibuAppDatabase

        override fun onCreate() {
            super.onCreate()
            database = Room.databaseBuilder(this, UtibuAppDatabase::class.java, UtibuAppDatabase.DATABASE_NAME)
                .build()
        }

        // ... (other app initialization logic)
    }
}