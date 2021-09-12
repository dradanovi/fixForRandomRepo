package com.example.demo.mapper;

import com.example.demo.dto.CommentsDto;
import com.example.demo.model.Comment;


public class CommentMapper {


    public CommentsDto commentsDto(Comment comment) {

        CommentsDto commentsDto = new CommentsDto();

        commentsDto.setId(comment.getId());
        commentsDto.setCreatedDate(comment.getCreatedDate());
        commentsDto.setText(comment.getText());


        return commentsDto;
    }

    public Comment dtoToComment(CommentsDto commentsDto ) {

        Comment comment = new Comment();

        comment.setId(commentsDto.getId());
        comment.setCreatedDate(commentsDto.getCreatedDate());
        comment.setText(commentsDto.getText());


        return comment;
    }
    }