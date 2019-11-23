package com.example.sharbat.data.db.repositories

import com.example.sharbat.data.db.EventDao
import com.example.sharbat.data.db.model.EventModel
import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import io.reactivex.Observable

class RoomEventsRepository(
    private val dao: EventDao
) : EventsRepository {

    override fun save(events: List<Event>) {
        dao.saveEvents(events.map { it.toDataModel() })
    }

    override fun getAllWithUpdates(): Observable<List<Event>> {
        return dao.getEvents().toObservable().map { list ->
            list.map { it.toCore() }
        }
    }

    fun Event.toDataModel() = EventModel(
        id = id,
        title = title,
        text = text,
        time_stamp = updateTime.toString(),
        time_event = eventTime.toString(),
        image = image,
        place = place
    )

    fun EventModel.toCore() = Event(
        id = id,
        title = title,
        text = text,
        eventTime = time_event.toLong(),
        updateTime = time_stamp.toLong(),
        image = image,
        place = place
    )
}