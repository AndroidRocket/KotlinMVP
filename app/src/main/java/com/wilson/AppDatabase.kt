package com.wilson

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

import com.wilson.models.MetOfficeData
import com.wilson.models.dao.MetOfficeDataDao

@Database(entities = arrayOf(MetOfficeData::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase()
{
    abstract fun metOfficeDataDao() : MetOfficeDataDao
}