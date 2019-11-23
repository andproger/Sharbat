package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.entities.Event
import io.reactivex.Observable

interface GetEventsInteractor {
    fun get(): Observable<List<Event>>
}