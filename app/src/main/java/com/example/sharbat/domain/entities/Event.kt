package com.example.sharbat.domain.entities

import java.util.*

data class Event(
    val id: String,
    val title: String,
    val text: String,
    val place: String,
    val image: String,
    val eventTime: Date,
    val updateTime: Date,
    val link: String?
)