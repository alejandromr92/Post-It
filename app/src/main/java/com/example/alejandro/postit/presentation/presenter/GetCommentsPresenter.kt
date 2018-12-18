package com.example.alejandro.postit.presentation.presenter

import com.example.alejandro.postit.domain.model.Comment

interface GetCommentsPresenter {
    fun getComments(postId: Int)

    interface View: BaseView {
        fun onCommentsRetrieved(commentsList: List<Comment>)
        fun onCommentsRetrievingError()
    }
}