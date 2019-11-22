package com.example.sharbat.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sharbat.data.db.model.EventDataModel
import io.reactivex.Single

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveEvents(q: List<EventDataModel>)

    @Query("SELECT * FROM EventDataModel")
    suspend fun getEvents(): Single<List<EventDataModel>>
}