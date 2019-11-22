package com.example.sharbat.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sharbat.data.db.model.EventDataModel

@Database(entities = [EventDataModel::class], version = 1)

//@TypeConverters(class?)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}