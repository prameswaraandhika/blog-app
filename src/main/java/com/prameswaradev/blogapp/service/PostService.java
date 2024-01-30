package com.prameswaradev.blogapp.service;

import com.prameswaradev.blogapp.model.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAllPosts();
}
