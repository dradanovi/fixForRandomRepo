package com.example.demo.model;

import com.example.demo.exception.SpringRedditException;


import java.util.Arrays;


public enum VoteType {
    UPVOTE(1), DOWNVOTE(-1),
    ;

    private int direction;

    VoteType(int direction) {

    }


}
