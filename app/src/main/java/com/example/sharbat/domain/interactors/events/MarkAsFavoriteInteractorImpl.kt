package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.gateways.EventsRepository
import com.example.sharbat.domain.gateways.FavoriteEventsRepository
import io.reactivex.Completable

class MarkAsFavoriteInteractorImpl(
    private val favoriteEventsRepository: FavoriteEventsRepository
) : MarkAsFavoriteInteractor {
    override fun mark(id: String): Completable {
        return Completable.fromCallable {
            val favorite = favoriteEventsRepository.getEventById(id)

            favorite?.let {
                favoriteEventsRepository.delete(id)
            } ?: favoriteEventsRepository.save(id)
        }
    }
}