package com.example.alejandro.postit.network.model.dto

data class CommentDto (
    var postId: Int = 0,
    var email: String = "",
    var body: String = ""
)