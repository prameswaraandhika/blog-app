package com.prameswaradev.blogapp.model.mapper;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.model.entity.Post;

public class PostMapper {

    public PostDto mappingToPostDto(Post post){
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .url(post.getUrl())
                .description(post.getDescription())
                .content(post.getContent())
                .createdOn(post.getCreatedOn())
                .updatedOn(post.getUpdatedOn())
                .build();
    }

    public Post mappingToPost(PostDto postDto){
        return Post.builder()
                .id(postDto.getId())
                .title(postDto.getTitle())
                .url(postDto.getUrl())
                .description(postDto.getDescription())
                .content(postDto.getContent())
                .createdOn(postDto.getCreatedOn())
                .updatedOn(postDto.getUpdatedOn())
                .build();
    }
}
