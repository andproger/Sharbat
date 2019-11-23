package com.example.sharbat.data.db.repositories

import com.example.sharbat.data.db.datastores.FavoriteEventsDao
import com.example.sharbat.data.db.model.FavoriteEventModel
import com.example.sharbat.domain.gateways.FavoriteEventsRepository
import io.reactivex.Observable

class RoomFavoriteEventsRepository(
    private val favoriteEventsDao: FavoriteEventsDao
) : FavoriteEventsRepository {

    override fun save(id: String) {
        favoriteEventsDao.saveEvent(id.toDataModel())
    }

    override fun getEventById(id: String): String? {
        return favoriteEventsDao.getEventById(id)?.toCore()
    }

    override fun getAll(): List<String> {
        return favoriteEventsDao.getEvents().map { it.toCore() }
    }

    override fun getAllWirhUpdates(): Observable<List<String>> {
        return favoriteEventsDao.getEventsWithUpdates()
            .toObservable()
            .map { list ->
                list.map { it.toCore() }
            }
    }

    override fun delete(id: String) {
        favoriteEventsDao.deleteEvent(id)
    }

    private fun String.toDataModel() = FavoriteEventModel(this)

    private fun FavoriteEventModel.toCore() = this.id
}