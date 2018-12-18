package com.example.alejandro.postit.presentation.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.alejandro.postit.R
import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.presentation.extensions.inflate
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentListAdapter(private val items: List<Comment>): RecyclerView.Adapter<CommentHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentHolder =
        CommentHolder(parent.inflate(R.layout.item_comment))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: CommentHolder, position: Int) =
        holder.bind(items[position])
}


class CommentHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    fun bind(comment: Comment) = with(itemView){
        comment_item_author.text = comment.author
        comment_item_comment.text = comment.comment

    }
}