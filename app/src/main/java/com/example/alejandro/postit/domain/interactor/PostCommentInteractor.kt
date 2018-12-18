package com.example.alejandro.postit.domain.interactor

import com.example.alejandro.postit.domain.model.Comment

interface PostCommentInteractor {
    fun execute(comment: Comment, onComplete: (Comment) -> Unit, onError: (Throwable) -> Unit)
}