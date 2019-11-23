package com.example.sharbat.domain.gateways

import com.example.sharbat.domain.entities.Event
import io.reactivex.Observable
import io.reactivex.Single

interface EventsRepository {

    fun save(events: List<Event>)

    fun getAllWithUpdates(): Observable<List<Event>>

    fun getByIdWithUpdates(id: String): Single<Event>
}