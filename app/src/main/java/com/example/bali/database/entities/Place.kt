package com.example.bali.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class Place(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val address: String,
    val description: String,
    val placePhoto: String
)