package com.example.bali.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bali.database.dao.PlaceDao
import com.example.bali.database.dao.PostDao
import com.example.bali.database.dao.UserDao
import com.example.bali.database.entities.Place
import com.example.bali.database.entities.Post
import com.example.bali.database.entities.User

@Database(entities = [User::class, Place::class, Post::class], version = 3)
abstract class BaliDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun placeDao(): PlaceDao
    abstract fun postDao(): PostDao
}