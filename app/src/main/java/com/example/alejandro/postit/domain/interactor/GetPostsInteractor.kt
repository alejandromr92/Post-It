package com.example.alejandro.postit.domain.interactor

import com.example.alejandro.postit.domain.model.Post

interface GetPostsInteractor {
    fun execute(onComplete: (List<Post>) -> Unit, onError: (Throwable) -> Unit)
}