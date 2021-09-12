package com.example.demo.service;


import com.example.demo.dto.CommentsDto;
import com.example.demo.mapper.CommentMapper;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class CommentsService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;


    public void save(CommentsDto commentsDto){

       Comment save = commentRepository.findById(commentsDto.getId())
                .orElseThrow(() -> new RuntimeException("Not found"));

        commentRepository.save(comment);

    }
}
