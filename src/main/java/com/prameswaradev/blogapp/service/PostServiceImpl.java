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
import static java.util.stream.Collectors.toList;

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
                .collect(toList());
    }

    @Override
    public void create(PostDto postDto) {
        Post post = mappingToPost(postDto);
        postRepository.save(post);
    }

    @Override
    public PostDto findPostById(Long postId) {
        var post = postRepository.findById(postId).get();
        return PostMapper.mappingToPostDto(post);
    }

    @Override
    public void updatePost(PostDto postDto) {
        var post = PostMapper.mappingToPost(postDto) ;
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        var post = postRepository.findById(postId).get();
        postRepository.delete(post);
    }

    @Override
    public PostDto findByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mappingToPostDto(post);
    }

    @Override
    public List<PostDto> search(String query) {
        List<Post> post = postRepository.searchPost(query);
        return post.stream().map(PostMapper::mappingToPostDto)
                .collect(toList());
    }
}
