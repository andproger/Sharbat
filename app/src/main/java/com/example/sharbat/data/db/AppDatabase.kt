package com.example.sharbat.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.sharbat.data.db.datastores.EventDao
import com.example.sharbat.data.db.datastores.FavoriteEventsDao
import com.example.sharbat.data.db.model.FavoriteEventModel
import com.example.sharbat.data.db.model.EventModel

@Database(entities = [EventModel::class, FavoriteEventModel::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao

    abstract fun favoritEventDao(): FavoriteEventsDao
}