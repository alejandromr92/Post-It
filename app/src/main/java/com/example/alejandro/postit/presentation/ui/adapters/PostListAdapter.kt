package com.example.alejandro.postit.presentation.ui.adapters

import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.alejandro.postit.R
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.presentation.extensions.PostDetailIntent
import com.example.alejandro.postit.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_post.view.*

class PostListAdapter(private val items: List<Post>): RecyclerView.Adapter<PostHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder =
        PostHolder(parent.inflate(R.layout.item_post))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) =
            holder.bind(items[position])
}


class PostHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(post: Post) = with(itemView){
        post_item_title.text = post.title
        post_item_content.text = post.content
        setOnClickListener{
            startActivity(this.context, this.context.PostDetailIntent(post), null)
        }
    }
}