package com.example.sharbat.domain.gateways

import com.example.sharbat.domain.entities.Event
import io.reactivex.Observable

interface EventsRepository {

    fun save(events: List<Event>)

    fun getAll(): List<Event>

    fun getAllWithUpdates(): Observable<List<Event>>
}