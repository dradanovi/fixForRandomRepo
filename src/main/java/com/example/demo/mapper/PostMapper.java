package com.example.demo.mapper;

import com.example.demo.dto.PostDto;
import com.example.demo.model.Post;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class PostMapper {



    public PostDto postDto(Post post) {

        PostDto postToDto = new PostDto();

        postToDto.setId(post.getPostId());
        postToDto.setPostName(post.getPostName());
        postToDto.setUrl(post.getUrl());
        postToDto.setDescription(post.getDescription());

        return postToDto;
    }

    public Post dtoToPOst(PostDto postDto) {

        Post post = new Post();

        post.setPostId(postDto.getId());
        post.setPostName(postDto.getPostName());
        post.setUrl(postDto.getUrl());
        post.setDescription(postDto.getDescription());

        return post;
    }



}
