package com.example.sharbat.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EventDataModel(@PrimaryKey val id: String, val title: String, val text: String) //todo