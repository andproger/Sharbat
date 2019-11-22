package com.example.sharbat.data.network

import com.example.sharbat.data.network.entity.EventsResponse
import io.reactivex.Single
import retrofit2.http.GET

interface ClientApi {
    @GET
    fun getaAllEvents(): Single<EventsResponse>
}