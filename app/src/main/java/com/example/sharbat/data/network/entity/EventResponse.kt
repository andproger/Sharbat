package com.example.sharbat.data.network.entity

import com.google.gson.annotations.SerializedName

class EventResponse(
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("text")
    val text: String,

    @SerializedName("image")
    val image: String,

    @SerializedName("time_stamp")
    val timeStamp: Long,

    @SerializedName("time_event")
    val timeEvent: Long
)