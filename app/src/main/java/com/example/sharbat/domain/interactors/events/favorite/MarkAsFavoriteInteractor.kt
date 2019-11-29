package com.example.sharbat.domain.interactors.events.favorite

import io.reactivex.Completable

interface MarkAsFavoriteInteractor {

    fun mark(id: String): Completable
}