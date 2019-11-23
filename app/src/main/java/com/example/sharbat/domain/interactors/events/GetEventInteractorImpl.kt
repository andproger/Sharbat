package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import io.reactivex.Single

class GetEventInteractorImpl(
    private val eventsRepository: EventsRepository
) : GetEventInteractor {
    override fun get(id: String): Single<Event> {
        return eventsRepository.getByIdWithUpdates(id)
    }
}