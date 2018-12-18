package com.example.alejandro.postit.network.model.converter

import com.example.alejandro.postit.domain.model.Post
import com.example.alejandro.postit.network.model.dto.PostDto

abstract class PostMapper {
    companion object {
        fun map(postList: List<PostDto>) : List<Post> {
            val list = ArrayList<Post>()

            if (postList.isNotEmpty()){
                for (p in postList){
                    list.add(map(p))
                }
            }

            return list
        }

        private fun map(dto: PostDto): Post {
            val result = Post()

            result.title = dto.title
            result.content = dto.body

            return result
        }
    }
}