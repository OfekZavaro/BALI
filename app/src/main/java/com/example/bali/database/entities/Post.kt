package com.example.bali.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "posts")
data class Post(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val userId: Int,
    val comment: String,
    val photo: String,
    val placeName: String
)