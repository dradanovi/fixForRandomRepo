package com.example.demo.mapper;


import com.example.demo.dto.SubredditDto;
import com.example.demo.model.Post;
import com.example.demo.model.Subreddit;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubredditMapper {

    public SubredditDto mapSubredditToSubredditDto(Subreddit subreddit) {

        SubredditDto subredditDto = new SubredditDto();
        subredditDto.setId(subreddit.getId());
        subredditDto.setName(subreddit.getName());
        subredditDto.setDescription(subreddit.getDescription());
        subredditDto.setNumberOfPosts(countThePosts(subreddit.getPosts()));

        return subredditDto;
    }

    public Integer countThePosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }


    public Subreddit mapDtoSubredditToSubreddit(SubredditDto subredditDto) {

        Subreddit subreddit = new Subreddit();
        subreddit.setId(subredditDto.getId());
        subreddit.setName(subredditDto.getName());
        subreddit.setDescription(subredditDto.getDescription());


        return subreddit;
    }


}


