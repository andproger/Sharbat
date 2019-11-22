package com.example.sharbat.domain.interactors.events

import io.reactivex.Completable

interface RefreshEventsInteractor {
    fun refresh(): Completable
}