package com.example.alejandro.postit.presentation.ui.activities

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.alejandro.postit.R
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.presentation.presenter.GetPostsPresenter
import com.example.alejandro.postit.presentation.presenter.impl.GetPostsPresenterImpl
import com.example.alejandro.postit.presentation.ui.adapters.PostListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*
import java.util.ArrayList

class PostListActivity: BaseActivity(), GetPostsPresenter.View {
    private var getPostsPresenter: GetPostsPresenter? = null

    private var postListAdapter: PostListAdapter? = null

    private var postList: MutableList<Post>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.layout = R.layout.activity_list
        super.onCreate(savedInstanceState)
    }

    override fun initializePresenters() {
        super.initializePresenters()

        this.getPostsPresenter = GetPostsPresenterImpl(
            Schedulers.newThread(),
            AndroidSchedulers.mainThread(),
            this
        )
    }

    override fun configViews() {
        super.configViews()

        this.configRecyclerView()

        this.configErrorMessage()
    }

    private fun configRecyclerView() {
        this.postList = ArrayList()

        val layoutManager = LinearLayoutManager(this)
        posts_list.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(posts_list.context, layoutManager.orientation)
        posts_list.addItemDecoration(dividerItemDecoration)

        this.postListAdapter = PostListAdapter(postList as ArrayList<Post>)
        posts_list.adapter = postListAdapter
    }

    private fun configErrorMessage(){
        //TODO show error message and retry function
    }

    override fun loadData() {
        super.loadData()

        this.getPostsPresenter!!.getPosts()
    }

    override fun onPostsRetrieved(retrievedPosts: List<Post>) {
        for (post in retrievedPosts){
            if (!this.postList!!.contains(post)){
                this.postList!!.add(post)
            }
        }

        this.postListAdapter!!.notifyDataSetChanged()
    }

    override fun onPostsRetrievingError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}