package com.example.sharbat.domain.gateways

import io.reactivex.Observable

interface FavoriteEventsRepository {
    fun save(id: String)

    fun getEventById(id: String): String?

    fun getAll(): List<String>

    fun getAllWirhUpdates(): Observable<List<String>>

    fun delete(id: String)
}