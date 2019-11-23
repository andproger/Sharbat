package com.example.sharbat.domain.entities

data class Event(
    val id: Int,
    val title: String,
    val text: String,
    val place: String,
    val image: String,
    val eventTime: Long,
    val updateTime: Long
)