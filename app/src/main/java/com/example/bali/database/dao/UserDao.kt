package com.example.bali.database.dao

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bali.database.entities.User

interface UserDao {
    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): User?
    @Insert
    fun insertUser(user: User)
    @Update
    fun updateUser(user: User)
    @Query("SELECT * FROM users WHERE email = :email")
    fun getUserByEmail(email: String): User?
}