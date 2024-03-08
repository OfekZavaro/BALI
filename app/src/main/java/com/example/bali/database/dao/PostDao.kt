package com.example.bali.database.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.bali.database.entities.Post

interface PostDao {
    @Insert
    fun insertPost(post: Post)

    @Update
    fun updatePost(post: Post)

    @Delete
    fun deletePost(post: Post)

    @Query("SELECT * FROM posts")
    fun getAllPosts(): List<Post>

    @Query("SELECT * FROM posts WHERE id = :postId")
    fun getPostById(postId: Int): Post?

    @Query("SELECT * FROM posts WHERE userId = :userId ORDER BY date DESC")
    fun getPostsByUserId(userId: Int): List<Post>
}