package com.example.alejandro.postit.domain.interactor.impl

import com.example.alejandro.postit.domain.interactor.GetPostsInteractor
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.network.api.impl.JSONPlaceholderAPIImpl
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposables

class GetPostsInteractorImpl(
    private val observeOn: Scheduler,
    private val subscribeOn: Scheduler
): GetPostsInteractor {
    private val api = JSONPlaceholderAPIImpl()

    private var subscription = Disposables.empty()

    override fun execute(onComplete: (List<Post>) -> Unit, onError: (Throwable) -> Unit) {
        subscription = api.getPosts()
            .subscribeOn(subscribeOn)
            .observeOn(observeOn)
            .subscribe(onComplete, onError)
    }
}