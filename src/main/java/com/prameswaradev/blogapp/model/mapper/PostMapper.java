package com.prameswaradev.blogapp.model.mapper;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.model.entity.Post;

public class PostMapper {

    public static PostDto mappingToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .description(post.getDescription())
                .content(post.getContent())
                .build();
    }

    public static Post mappingToPost(PostDto postDto){
        return Post.builder()
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .description(postDto.getDescription())
                .content(postDto.getContent())
                .build();
    }
}
