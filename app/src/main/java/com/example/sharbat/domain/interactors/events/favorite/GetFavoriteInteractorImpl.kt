package com.example.sharbat.domain.interactors.events.favorite

import com.example.sharbat.domain.gateways.FavoriteEventsRepository
import io.reactivex.Observable

class GetFavoriteInteractorImpl(
    private val favoriteEventsRepository: FavoriteEventsRepository
) : GetFavoriteInteractor {

    override fun get(): Observable<List<String>> {
        return favoriteEventsRepository.getAllWirhUpdates()
    }
}