package com.example.demo.mapper;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostSaveRequest;
import com.example.demo.model.Post;
import com.example.demo.model.Subreddit;
import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;


@Service
@AllArgsConstructor
public class PostMapper {

    private final SubredditMapper subredditMapper;

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

    public Post PostReqToEnt(PostSaveRequest saveRequest, User user, Subreddit subreddit) {
        return Post.builder()
                .postId(saveRequest.getId())
                .createdDate(Instant.now())
                .postName(saveRequest.getPostName())
                .description(saveRequest.getDescription())
                .url(saveRequest.getUrl())
                .subreddit(subreddit)
                .user(user)
                .build();
    }

    public PostDto PostEntToDto(Post post) {
        return PostDto.builder()
                .id(post.getPostId())
                .postName(post.getPostName())
                .description(post.getDescription())
                .url(post.getUrl())
                .userId(post.getUser().getUserId())
                .username(post.getUser().getUsername())
                .subredditDto(this.subredditMapper.mapSubredditToSubredditDto(post.getSubreddit()))
                .build();
    }


}
