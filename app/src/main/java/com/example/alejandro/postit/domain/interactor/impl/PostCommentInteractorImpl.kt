package com.example.alejandro.postit.domain.interactor.impl

import com.example.alejandro.postit.domain.interactor.PostCommentInteractor
import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.network.api.impl.JSONPlaceholderAPIImpl
import com.example.alejandro.postit.network.model.converter.CommentMapper
import com.example.alejandro.postit.network.model.dto.CommentDto
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposables

class PostCommentInteractorImpl(
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
) : PostCommentInteractor {
    private val api = JSONPlaceholderAPIImpl()

    private var subscription = Disposables.empty()

    override fun execute(comment: Comment, onComplete: (Comment) -> Unit, onError: (Throwable) -> Unit) {
        subscription = api.postComment(CommentMapper.mapToDto(comment))
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }
}