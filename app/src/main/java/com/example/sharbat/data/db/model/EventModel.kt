package com.example.sharbat.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventModel(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val time_stamp: String,
    val time_event: String,
    val image: String
) //todo

//{
//    "id": 0,
//    "title": "qqq",
//    "text": "qqq",
//    "time_stamp": 131313131,
//    "time_event": 123131123
//    "image": "https://"
//}