package com.example.alejandro.postit.presentation.presenter.impl

import com.example.alejandro.postit.domain.interactor.impl.PostCommentInteractorImpl
import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.presentation.presenter.PostCommentPresenter
import io.reactivex.Scheduler

class PostCommentPresenterImpl(
    private val threadExecutor: Scheduler,
    private val mainThread: Scheduler,
    private val view: PostCommentPresenter.View
): PostCommentPresenter {
    override fun postComment(comment: Comment) {
        view.showProgress()
        val interactor = PostCommentInteractorImpl(mainThread, threadExecutor)
        interactor.execute(comment, ::onCommentPosted, ::onPostingCommentError)
    }

    private fun onCommentPosted(comment: Comment){
        view.hideProgress()
        view.onCommentPosted()
    }

    private fun onPostingCommentError(throws: Throwable){
        view.hideProgress()
        view.onPostingCommentError()
    }
}