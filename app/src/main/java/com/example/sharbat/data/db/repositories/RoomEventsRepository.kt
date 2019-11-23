package com.example.sharbat.data.db.repositories

import com.example.sharbat.data.db.datastores.EventDao
import com.example.sharbat.data.db.model.EventModel
import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import com.example.sharbat.domain.utils.toDate
import com.example.sharbat.domain.utils.toDateString
import com.example.sharbat.domain.utils.toDateText
import io.reactivex.Observable
import io.reactivex.Single

class RoomEventsRepository(
    private val dao: EventDao
) : EventsRepository {

    override fun save(events: List<Event>) {
        dao.saveEvents(events.map { it.toDataModel() })
    }

    override fun getAllWithUpdates(): Observable<List<Event>> {
        return dao.getEventsWithUpdates().toObservable().map { list ->
            list.map { it.toCore() }
        }
    }

    override fun getById(id: String): Event {
        return dao.getEvent(id).toCore()
    }

    override fun getByIdWithUpdates(id: String): Observable<Event> {
        return dao.getEventsWithUpdates()
            .toObservable()
            .map { list ->
                list.first { it.id == id }.toCore()
            }
    }

    fun Event.toDataModel() = EventModel(
        id = id,
        title = title,
        text = text,
        time_stamp = updateTime.toDateString(),
        time_event = eventTime.toDateString(),
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