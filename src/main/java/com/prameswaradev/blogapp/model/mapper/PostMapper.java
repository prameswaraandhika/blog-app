package com.prameswaradev.blogapp.model.mapper;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.model.entity.Post;

public class PostMapper {

    public PostDto mappingToPostDto(Post post){
        return PostDto.builder()

                .build();
    }
}
