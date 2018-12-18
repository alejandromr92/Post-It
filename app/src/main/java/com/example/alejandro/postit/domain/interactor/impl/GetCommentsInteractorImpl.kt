package com.example.alejandro.postit.domain.interactor.impl

import com.example.alejandro.postit.domain.interactor.GetCommentsInteractor
import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.network.api.impl.JSONPlaceholderAPIImpl
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposables

class GetCommentsInteractorImpl(
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
): GetCommentsInteractor {
    private val api = JSONPlaceholderAPIImpl()

    private var subscription = Disposables.empty()

    override fun execute(postId: Int, onComplete: (List<Comment>) -> Unit, onError: (Throwable) -> Unit) {
        subscription = api.getComments(postId)
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }
}