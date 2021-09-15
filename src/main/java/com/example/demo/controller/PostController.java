package com.example.demo.controller;

import com.example.demo.dto.PostDto;
import com.example.demo.dto.PostSaveRequest;
import com.example.demo.mapper.PostMapper;
import com.example.demo.model.Post;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.PostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostMapper postMapper;


    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostSaveRequest saveRequest) {
        PostDto postDto = postService.save(saveRequest);
        return ResponseEntity.ok().body(postDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPost(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostDto(id));
    }



    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAll());
    }

    @GetMapping("by-subreddit/{id}")
    public ResponseEntity<List<PostDto>> getPostsBySubreddit(Long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostsBySubredditId(id));
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<PostDto>> getPostsByUsername(String username){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostByUsername(username));
    }

}
