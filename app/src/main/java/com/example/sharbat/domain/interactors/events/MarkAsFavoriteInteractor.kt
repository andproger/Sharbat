package com.example.sharbat.domain.interactors.events

import io.reactivex.Completable

interface MarkAsFavoriteInteractor {

    fun mark(id: String): Completable
}