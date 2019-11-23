package com.example.sharbat.data.db.datastores

import androidx.room.*
import com.example.sharbat.data.db.model.EventModel
import io.reactivex.Flowable

@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEvents(list: List<EventModel>)

    @Query("SELECT * FROM EventModel")
    fun getEvents(): List<EventModel>

    @Query("SELECT * FROM EventModel")
    fun getEventsWithUpdates(): Flowable<List<EventModel>>

    @Query("SELECT * FROM EventModel WHERE id = :id")
    fun getEvent(id: String): EventModel

    @Query("DELETE FROM EventModel WHERE id < :id")
    fun deleteEvent(id: String): Int
}