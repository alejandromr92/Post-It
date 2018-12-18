package com.example.alejandro.postit.domain.model

data class Comment (
    var postId: Int = 0,
    var author: String = "",
    var comment: String = "")