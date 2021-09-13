package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.exception.SubredditNotFoundException;
import com.example.demo.mapper.PostMapper;
import com.example.demo.model.Post;

import com.example.demo.model.Subreddit;
import com.example.demo.model.User;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.SubredditRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    private final SubredditRepository subredditRepository;

    @Transactional
    public PostDto save(PostDto postDto ) {

        Post save = postRepository.save(postMapper.dtoToPOst(postDto));
        postDto.setId(save.getPostId());
        return postDto;

    }


    @Transactional
    public PostDto getPostDto (Long id){
        Post getPostById = postRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("No such data found"));
        return  postMapper.postDto(getPostById);
    }


    @Transactional(readOnly = true)
    public List<PostDto> getAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::postDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<PostDto> getPostsBySubredditId(Long subredditId){
        Subreddit subreddit = subredditRepository.findById(subredditId)
                .orElseThrow(() -> new SubredditNotFoundException(subredditId.toString()));
                List<Post> posts = postRepository.findAllBySubreddit(subreddit);
        return posts
                .stream()
                .map(postMapper :: postDto)
                .collect(toList());

    }



    @Transactional
    public List<PostDto> getPostByUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return postRepository.findByUser(user)
                .stream()
                .map(postMapper::postDto)
                .collect(toList());

    }



}
