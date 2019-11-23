package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import io.reactivex.Observable

class GetMyEventsInteractorImpl(
    private val eventsRepository: EventsRepository
) : GetEventsInteractor {
    override fun get(): Observable<List<Event>> {
        return eventsRepository.getAllWithUpdates()
    }
}