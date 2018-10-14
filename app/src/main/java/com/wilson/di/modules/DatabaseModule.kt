package com.wilson.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.wilson.AppDatabase
import com.wilson.models.dao.MetOfficeDataDao

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val context: Context) {

    val DB_FILE_NAME = "kisanhub.db"

    @Provides @Singleton
    fun provideContactDao(appDatabase: AppDatabase) : MetOfficeDataDao = appDatabase.metOfficeDataDao()

    @Provides @Singleton
    fun privideAppDatabase() : AppDatabase =

            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_FILE_NAME)
                    .build()



}