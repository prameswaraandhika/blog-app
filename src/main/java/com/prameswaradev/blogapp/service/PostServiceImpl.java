package com.prameswaradev.blogapp.service;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.model.entity.Post;
import com.prameswaradev.blogapp.model.mapper.PostMapper;
import com.prameswaradev.blogapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.prameswaradev.blogapp.model.mapper.PostMapper.mappingToPost;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{

    private final PostRepository postRepository;

    @Override
    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts
                .stream()
                .map(PostMapper::mappingToPostDto)
                .collect(Collectors.toList());
    }

    @Override
    public void create(PostDto postDto) {
        Post post = mappingToPost(postDto);
        postRepository.save(post);
    }
}
