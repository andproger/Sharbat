package com.example.sharbat.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteEventModel(
    @PrimaryKey val id: String
)