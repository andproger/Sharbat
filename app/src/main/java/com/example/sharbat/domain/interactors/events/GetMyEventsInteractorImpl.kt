package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.entities.Event
import com.example.sharbat.domain.gateways.EventsRepository
import com.example.sharbat.domain.gateways.FavoriteEventsRepository
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class GetMyEventsInteractorImpl(
    private val eventsRepository: EventsRepository,
    private val favoriteEventsRepository: FavoriteEventsRepository
) : GetEventsInteractor {
    override fun get(): Observable<List<Event>> {
        return Observable.combineLatest(
            favoriteEventsRepository.getAllWirhUpdates(),
            eventsRepository.getAllWithUpdates(),
            BiFunction { favorites: List<String>, events: List<Event> ->
                events.filter { favorites.contains(it.id) }
            }
        )
    }
}