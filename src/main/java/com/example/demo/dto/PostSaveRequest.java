package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSaveRequest {

    private Long id;
    private String postName;
    private String description;
    private String url;
    private Long userId;
    private Long subredditId;
}
