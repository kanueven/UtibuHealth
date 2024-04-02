package com.rae.utibuhealth

import android.app.Application
import androidx.room.Room
import com.rae.utibuhealth.data.UtibuAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton




class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
