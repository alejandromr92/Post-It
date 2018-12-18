package com.example.alejandro.postit.presentation.ui.activities

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.alejandro.postit.R
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.presentation.presenter.GetPostsPresenter
import com.example.alejandro.postit.presentation.presenter.impl.GetPostsPresenterImpl
import com.example.alejandro.postit.presentation.ui.adapters.PostListAdapter
import com.example.alejandro.postit.utils.LoggerUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

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
        list_error.setOnClickListener {
            loadData()
        }
    }

    private fun displayContent(postsObtained: Boolean){
        if (postsObtained){
            posts_list.visibility = View.VISIBLE
            list_error.visibility = View.GONE
        } else {
            posts_list.visibility = View.GONE
            list_error.visibility = View.VISIBLE
        }
    }

    override fun loadData() {
        super.loadData()

        this.getPostsPresenter!!.getPosts()
    }

    override fun onPostsRetrieved(retrievedPosts: List<Post>) {
        displayContent(true)
        for (post in retrievedPosts){
            if (!this.postList!!.contains(post)){
                this.postList!!.add(post)
            }
        }

        this.postListAdapter!!.notifyDataSetChanged()
    }

    override fun onPostsRetrievingError() {
        displayContent(false)
        LoggerUtils.logError("PostListActivity", "errorCode", Exception())
    }
}