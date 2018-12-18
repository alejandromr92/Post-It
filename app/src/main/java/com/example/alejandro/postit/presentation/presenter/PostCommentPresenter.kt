package com.example.alejandro.postit.presentation.presenter

import com.example.alejandro.postit.domain.model.Comment

interface PostCommentPresenter {
    fun postComment(comment: Comment)

    interface View: BaseView {
        fun onCommentPosted()
        fun onPostingCommentError()
    }
}