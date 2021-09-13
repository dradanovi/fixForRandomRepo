package com.example.demo.service;


import com.example.demo.dto.SubredditDto;
import com.example.demo.exception.SpringRedditException;
import com.example.demo.mapper.SubredditMapper;
import com.example.demo.model.Subreddit;
import com.example.demo.repository.SubredditRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
@AllArgsConstructor
@Slf4j

public class SubredditService {


    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;


    @Transactional
    public SubredditDto saveIt(SubredditDto subredditDto) {

        Subreddit save;
        Subreddit dtoToSubreddit;

        dtoToSubreddit = subredditMapper.mapDtoSubredditToSubreddit(subredditDto);
        save = subredditRepository.save(dtoToSubreddit);
        subredditDto.setId((save.getId()));


        return subredditDto;
    }


    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToSubredditDto)
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit fount with that name" + id));
        return subredditMapper.mapSubredditToSubredditDto(subreddit);
    }


}
