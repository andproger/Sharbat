package com.example.sharbat.domain.interactors.events

import com.example.sharbat.domain.entities.Event
import io.reactivex.Single

interface GetEventInteractor {
    fun get(id: String): Single<Event>
}