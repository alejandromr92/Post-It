package com.example.alejandro.postit.presentation.presenter.impl

import com.example.alejandro.postit.domain.interactor.impl.GetPostsInteractorImpl
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.presentation.presenter.GetPostsPresenter
import io.reactivex.Scheduler

class GetPostsPresenterImpl(
    private val threadExecutor: Scheduler,
    private val mainThread: Scheduler,
    private val view: GetPostsPresenter.View
): GetPostsPresenter {
    override fun getPosts() {
        view.showProgress()
        val interactor = GetPostsInteractorImpl(mainThread, threadExecutor)
        interactor.execute(::onPostsRetrieved, ::onPostsRetrievingError)
    }

    private fun onPostsRetrieved(postList: List<Post>){
        view.hideProgress()
        view.onPostsRetrieved(postList)
    }

    private fun onPostsRetrievingError(throwable: Throwable){
        view.hideProgress()
        view.onPostsRetrievingError()
    }
}