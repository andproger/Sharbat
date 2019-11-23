package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import com.example.sharbat.domain.gateways.FavoriteEventsRepository
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.functions.BiFunction

class GetEventInteractorImpl(
    private val eventsRepository: EventsRepository,
    private val favoriteEventsRepository: FavoriteEventsRepository
) : GetEventInteractor {

    override fun get(id: String): Observable<EventContract> {
        return Observable.combineLatest(
            favoriteEventsRepository.getAllWirhUpdates(),
            eventsRepository.getByIdWithUpdates(id),
            BiFunction { favorites: List<String>, event: Event ->
                EventContract(
                    event,
                    favorites.contains(event.id)
                )
            }
        )
    }
}