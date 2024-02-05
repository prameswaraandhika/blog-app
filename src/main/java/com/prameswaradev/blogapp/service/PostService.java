package com.prameswaradev.blogapp.service;

import com.prameswaradev.blogapp.model.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();

    void create(PostDto postDto);

    PostDto findPostById(Long postId);

    void updatePost(PostDto postDto);

    void deletePost(Long postId);
}
