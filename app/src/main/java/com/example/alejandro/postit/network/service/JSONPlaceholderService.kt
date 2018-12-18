package com.example.alejandro.postit.network.service

import com.example.alejandro.postit.network.Constants
import com.example.alejandro.postit.network.model.dto.CommentDto
import com.example.alejandro.postit.network.model.dto.PostDto
import io.reactivex.Single
import retrofit2.http.*

interface JSONPlaceholderService {

    @GET(Endpoints.POSTS)
    fun getPosts(
    ): Single<List<PostDto>>

    @GET(Endpoints.COMMENTS)
    fun getComments(
        @Query(Constants.POST_ID) postId: Int
    ): Single<List<CommentDto>>

    @POST(Endpoints.COMMENTS)
    fun postComment(
        @Body comment: CommentDto
    ): Single<CommentDto>
}