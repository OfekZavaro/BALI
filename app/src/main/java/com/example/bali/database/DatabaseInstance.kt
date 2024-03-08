package com.example.bali.database

import android.content.Context
import androidx.room.Room

object DatabaseInstance {

    @Volatile
    private var INSTANCE: BaliDatabase? = null

    fun getDatabase(context: Context): BaliDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                BaliDatabase::class.java,
                "bali_database"
            ).build()

            INSTANCE = instance
            instance
        }
    }
}