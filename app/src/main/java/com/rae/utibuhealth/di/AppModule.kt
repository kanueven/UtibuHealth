package com.rae.utibuhealth.di
import android.app.Application
import androidx.room.Room
import com.rae.utibuhealth.data.UtibuAppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): UtibuAppDatabase {
        return Room.databaseBuilder(app, UtibuAppDatabase::class.java, UtibuAppDatabase.DATABASE_NAME)
            .build()
    }
}
