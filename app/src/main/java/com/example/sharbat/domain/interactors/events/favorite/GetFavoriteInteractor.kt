package com.example.sharbat.domain.interactors.events.favorite

import io.reactivex.Observable

interface GetFavoriteInteractor {
    fun get(): Observable<List<String>>
}