package com.example.alejandro.postit.network.api

import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.network.model.dto.CommentDto
import io.reactivex.Single

interface JSONPlaceholderAPI {
    fun getPosts(): Single<List<Post>>
    fun getComments(postId: Int): Single<List<Comment>>
    fun postComment(comment: CommentDto): Single<Unit>
}