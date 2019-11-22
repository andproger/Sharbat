package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import io.reactivex.Observable

class GetAllEventsInteractorImpl(
    private val eventsRepository: EventsRepository
) : GetAllEventsInteractor {
    override fun get(): Observable<List<Event>> {
        return eventsRepository.getAllWithUpdates()
    }
}