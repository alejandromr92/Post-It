package com.example.alejandro.postit.presentation.ui.activities

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.alejandro.postit.R
import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.presentation.Constants
import com.example.alejandro.postit.presentation.presenter.GetCommentsPresenter
import com.example.alejandro.postit.presentation.presenter.PostCommentPresenter
import com.example.alejandro.postit.presentation.presenter.impl.GetCommentsPresenterImpl
import com.example.alejandro.postit.presentation.presenter.impl.PostCommentPresenterImpl
import com.example.alejandro.postit.presentation.ui.adapters.CommentListAdapter
import com.example.alejandro.postit.utils.LoggerUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_post_detail.*


class PostDetailActivity : BaseActivity(), GetCommentsPresenter.View, PostCommentPresenter.View {
    private var getCommentsPresenter: GetCommentsPresenter? = null
    private var postCommentsPresenter: PostCommentPresenter? = null

    private var commentListAdapter: CommentListAdapter? = null

    private var commentList: MutableList<Comment>? = null

    private var post : Post? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        this.layout = R.layout.activity_post_detail

        super.onCreate(savedInstanceState)

        this.post = intent.getParcelableExtra(Constants.POST_KEY)
    }

    override fun initializePresenters() {
        super.initializePresenters()

        this.getCommentsPresenter = GetCommentsPresenterImpl(
            Schedulers.newThread(),
            AndroidSchedulers.mainThread(),
            this
        )

        this.postCommentsPresenter = PostCommentPresenterImpl(
            Schedulers.newThread(),
            AndroidSchedulers.mainThread(),
            this
        )
    }

    override fun configViews() {
        super.configViews()

        post_title.text = post!!.title
        post_content.text = post!!.content

        this.configRecyclerView()

        this.configErrorMessage()

        this.configListeners()
    }

    private fun configRecyclerView() {
        this.commentList = ArrayList()

        val layoutManager = LinearLayoutManager(this)
        comments_list.layoutManager = layoutManager

        val dividerItemDecoration = DividerItemDecoration(comments_list.context, layoutManager.orientation)
        comments_list.addItemDecoration(dividerItemDecoration)

        this.commentListAdapter = CommentListAdapter(commentList as ArrayList<Comment>)
        comments_list.adapter = commentListAdapter
    }

    private fun configErrorMessage(){
        detail_error.setOnClickListener {
            loadData()
        }
    }

    private fun configListeners(){
        send_comment_btn.setOnClickListener {
            val newComment = Comment(author = "Alejandro", comment = new_comment_input.text.toString(), postId = post!!.id)
            commentList!!.add(newComment)
            postCommentsPresenter!!.postComment(newComment)
        }
    }

    private fun displayContent(commentsObtained: Boolean){
        if (commentsObtained){
            comments_list.visibility = View.VISIBLE
            detail_error.visibility = View.GONE
        } else {
            comments_list.visibility = View.GONE
            detail_error.visibility = View.VISIBLE
        }
    }

    override fun loadData() {
        super.loadData()

        this.getCommentsPresenter!!.getComments(post!!.id)
    }


    override fun onCommentsRetrieved(retrievedComments: List<Comment>) {
        displayContent(true)
        for (comment in retrievedComments){
            if (!this.commentList!!.contains(comment)){
                this.commentList!!.add(comment)
            }
        }

        this.commentListAdapter!!.notifyDataSetChanged()
    }

    override fun onCommentsRetrievingError() {
        displayContent(false)
        LoggerUtils.logError("PostDetailActivity", "errorCode", Exception())
    }

    override fun onCommentPosted() {
        new_comment_input.text.clear()

        detail_content_layout.requestFocus()

        this.getCommentsPresenter!!.getComments(post!!.id)
    }

    override fun onPostingCommentError() {
        // XXX Do nothing since the API just fakes the post
    }

}
