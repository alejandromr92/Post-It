package com.example.alejandro.postit.network.model.converter

import com.example.alejandro.postit.domain.model.Comment
import com.example.alejandro.postit.network.model.dto.CommentDto

abstract class CommentMapper {
    companion object {
        fun map(commentList: List<CommentDto>) : List<Comment> {
            val list = ArrayList<Comment>()

            if (commentList.isNotEmpty()){
                for (c in commentList){
                    list.add(map(c))
                }
            }

            return list
        }

        fun mapToDto(comment: Comment): CommentDto {
            val dto = CommentDto()

            dto.postId = comment.postId
            dto.email = comment.author
            dto.body = comment.comment

            return dto

        }


        fun map(dto: CommentDto): Comment {
            val result = Comment()

            result.postId = dto.postId
            result.author = dto.email
            result.comment = dto.body

            return result
        }
    }
}