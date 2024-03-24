package com.example.bali.profile

data class Comment(
    val commentId: String,
    val placeName: String,
    val comment: String,
    var photoUrl: String
)