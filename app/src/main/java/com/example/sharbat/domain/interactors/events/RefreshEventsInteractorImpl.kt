package com.example.sharbat.domain.interactors.events

import com.example.sharbat.data.db.model.EventModel
import com.example.sharbat.data.network.ClientApi
import com.example.sharbat.data.network.entity.EventResponse
import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import io.reactivex.Completable

class RefreshEventsInteractorImpl(
    private val clientApi: ClientApi,
    private val eventsRepository: EventsRepository
) : RefreshEventsInteractor {

    override fun refresh(): Completable {
        return clientApi.getAllEvents()
            .flatMapCompletable { response ->
                val events = response.events.map { it.toCore() }

                eventsRepository.save(events)

                Completable.complete()
            }
    }

    fun EventResponse.toCore() = Event(
        id = id,
        title = title,
        text = text,
        image = image,
        eventTime = timeEvent,
        updateTime = timeStamp
    )
}