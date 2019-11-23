package com.example.sharbat.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventModel(
    @PrimaryKey val id: String,
    val title: String,
    val text: String,
    val time_stamp: String,
    val time_event: String,
    val image: String,
    val place: String,
    val link: String?
)