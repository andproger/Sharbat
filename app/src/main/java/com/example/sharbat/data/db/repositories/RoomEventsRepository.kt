package com.example.sharbat.data.db.repositories

import com.example.sharbat.data.db.EventDao
import com.example.sharbat.data.db.model.EventModel
import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import com.example.sharbat.domain.utils.toDate
import io.reactivex.Observable
import io.reactivex.Single

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

    override fun getByIdWithUpdates(id: String): Single<Event> {
        return dao.getEvent(id).flatMapSingle {
            Single.just(it.toCore())
        }
    }

    fun Event.toDataModel() = EventModel(
        id = id,
        title = title,
        text = text,
        time_stamp = updateTime.toString(),
        time_event = eventTime.toString(),
        image = image,
        place = place,
        link = link
    )

    fun EventModel.toCore() = Event(
        id = id,
        title = title,
        text = text,
        eventTime = time_event.toDate(),
        updateTime = time_stamp.toDate(),
        image = image,
        place = place,
        link = link
    )
}