package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.entities.Event
import io.reactivex.Observable
import io.reactivex.Single

interface GetEventInteractor {
    fun get(id: String): Observable<Event>
}