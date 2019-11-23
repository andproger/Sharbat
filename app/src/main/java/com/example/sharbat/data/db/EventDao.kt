package com.example.sharbat.data.db

import androidx.room.*
import com.example.sharbat.data.db.model.EventModel
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEvents(list: List<EventModel>)

    @Query("SELECT * FROM EventModel")
    fun getEvents(): Flowable<List<EventModel>> //todo

    @Query("SELECT * FROM EventModel WHERE id = :id")
    fun getEvent(id: String): Maybe<EventModel>

    @Query("DELETE FROM eventmodel WHERE time_event < :time")
    fun deleteEventsByTime(time: String): Int
}