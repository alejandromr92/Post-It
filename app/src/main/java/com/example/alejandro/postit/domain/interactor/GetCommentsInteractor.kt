package com.example.alejandro.postit.domain.interactor

import com.example.alejandro.postit.domain.model.Comment

interface GetCommentsInteractor {
    fun execute(postId: Int, onComplete: (List<Comment>) -> Unit, onError: (Throwable) -> Unit)
}