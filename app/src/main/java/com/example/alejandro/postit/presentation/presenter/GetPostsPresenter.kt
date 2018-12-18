package com.example.alejandro.postit.presentation.presenter

import com.example.alejandro.postit.domain.model.Post

interface GetPostsPresenter {
    fun getPosts()

    interface View: BaseView {
        fun onPostsRetrieved(postList: List<Post>)
        fun onPostsRetrievingError()
    }
}