package com.example.sharbat.domain.gateways

import com.example.sharbat.domain.entities.Event
import io.reactivex.Observable
import io.reactivex.Single

interface EventsRepository {

    fun save(events: List<Event>)

    fun getAllWithUpdates(): Observable<List<Event>>

    fun getById(id: String): Event

    fun getByIdWithUpdates(id: String): Observable<Event>
}