package com.example.alejandro.postit.presentation.extensions

import android.content.Context
import android.content.Intent
import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.presentation.Constants
import com.example.alejandro.postit.presentation.ui.activities.PostDetailActivity

fun Context.PostDetailIntent(post: Post): Intent {
    return Intent(this, PostDetailActivity::class.java).apply {
        putExtra(Constants.POST_KEY, post)
    }
}