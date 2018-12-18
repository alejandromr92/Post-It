package com.example.alejandro.postit.network.api.impl

import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.network.api.JSONPlaceholderAPI
import com.example.alejandro.postit.network.interceptor.NonSecurityInterceptor
import com.example.alejandro.postit.network.model.converter.CommentMapper
import com.example.alejandro.postit.network.model.converter.PostMapper
import com.example.alejandro.postit.network.model.dto.CommentDto
import com.example.alejandro.postit.network.service.Endpoints
import com.example.alejandro.postit.network.service.JSONPlaceholderService
import com.example.alejandro.postit.network.util.RetrofitClient
import io.reactivex.Single

class JSONPlaceholderAPIImpl: JSONPlaceholderAPI {
    private val service: JSONPlaceholderService = RetrofitClient.getJSONPlaceholderService(Endpoints.BASE_URL, NonSecurityInterceptor())

    override fun getPosts(): Single<List<Post>> =
        service.getPosts().map { PostMapper.map(it) }

    override fun getComments(postId: Int): Single<List<Comment>> =
        service.getComments(postId).map { CommentMapper.map(it) }


    override fun postComment(comment: CommentDto): Single<Unit> =
        service.postComment(comment)

}