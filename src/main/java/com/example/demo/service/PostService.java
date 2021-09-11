package com.example.demo.service;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.SubredditDto;
import com.example.demo.exception.SpringRedditException;
import com.example.demo.mapper.PostMapper;
import com.example.demo.model.Post;

import com.example.demo.repository.PostRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Transactional
    public PostDto save(PostDto postDto ) {

        Post save = postRepository.save(postMapper.dtoToPOst(postDto));
        postDto.setId(save.getPostId());
        return postDto;

    }

    @Transactional(readOnly = true)
    public List<PostDto> getAll() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::postDto)
                .collect(toList());
    }


}
