package com.example.demo.controller;


import com.example.demo.dto.CommentsDto;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RequestMapping("/api/comments/")
@RestController

public class CommentsController {

    @PostMapping
    public void createComment(@RequestBody CommentsDto commentsDto){

    }
}
