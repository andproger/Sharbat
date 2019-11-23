package com.example.sharbat.data.db.datastores

import androidx.room.*
import com.example.sharbat.data.db.model.FavoriteEventModel
import io.reactivex.Flowable
import io.reactivex.Maybe

@Dao
interface FavoriteEventsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEvent(event: FavoriteEventModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveEvents(list: List<FavoriteEventModel>)

    @Query("SELECT * FROM FavoriteEventModel WHERE id < :id")
    fun getEventById(id: String): FavoriteEventModel?

    @Query("SELECT * FROM FavoriteEventModel")
    fun getEvents(): List<FavoriteEventModel>

    @Query("SELECT * FROM FavoriteEventModel")
    fun getEventsWithUpdates(): Flowable<List<FavoriteEventModel>>

    @Query("SELECT * FROM FavoriteEventModel WHERE id = :id")
    fun getEvent(id: String): FavoriteEventModel

    @Query("DELETE FROM FavoriteEventModel WHERE id < :id")
    fun deleteEvent(id: String): Int
}