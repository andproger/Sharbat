package com.example.sharbat.data.network.entity

import com.google.gson.annotations.SerializedName

class AllEventsResponse(
    @SerializedName("events")
    val events: List<EventResponse>
)