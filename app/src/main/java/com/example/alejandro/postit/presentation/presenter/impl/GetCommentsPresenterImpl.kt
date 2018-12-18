package com.example.alejandro.postit.presentation.presenter.impl

import com.example.alejandro.postit.domain.interactor.impl.GetCommentsInteractorImpl
import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.presentation.presenter.GetCommentsPresenter
import io.reactivex.Scheduler

class GetCommentsPresenterImpl(
    private val threadExecutor: Scheduler,
    private val mainThread: Scheduler,
    private val view: GetCommentsPresenter.View
): GetCommentsPresenter {
    override fun getComments(postId: Int) {
        view.showProgress()
        val interactor = GetCommentsInteractorImpl(mainThread, threadExecutor)
        interactor.execute(postId, ::onCommentsRetrieved, ::onCommentsRetrievingError)
    }

    private fun onCommentsRetrieved(commentsList: List<Comment>){
        view.hideProgress()
        view.onCommentsRetrieved(commentsList)
    }

    private fun onCommentsRetrievingError(throwable: Throwable){
        view.hideProgress()
        view.onCommentsRetrievingError()
    }

}