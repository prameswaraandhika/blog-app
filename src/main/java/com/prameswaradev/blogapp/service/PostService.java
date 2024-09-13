package com.prameswaradev.blogapp.service;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.model.entity.Category;
import com.prameswaradev.blogapp.model.entity.Post;
import com.prameswaradev.blogapp.model.mapper.PostMapper;
import com.prameswaradev.blogapp.repository.CategoryRepository;
import com.prameswaradev.blogapp.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.prameswaradev.blogapp.model.mapper.PostMapper.mappingToPost;
import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public List<PostDto> findAllPosts() {
        List<Post> posts = postRepository.findAll();
        return posts
                .stream()
                .map(PostMapper::mappingToPostDto)
                .collect(toList());
    }

    public void create(PostDto postDto) {
        log.info("postDto: {}", postDto);
        Optional<Category> optionalCategory = categoryRepository.findById(postDto.getId());
        Post post = mappingToPost(postDto);
        optionalCategory.ifPresent(post::setCategory);
        postRepository.save(post);
    }

    public PostDto findPostById(String postId) {
        var post = postRepository.findById(postId).get();
        return PostMapper.mappingToPostDto(post);
    }

    public void updatePost(PostDto postDto) {
        var post = PostMapper.mappingToPost(postDto) ;
        postRepository.save(post);
    }

    public void deletePost(String postId) {
        var post = postRepository.findById(postId).get();
        postRepository.delete(post);
    }

    public PostDto findByUrl(String postUrl) {
        Post post = postRepository.findByUrl(postUrl).get();
        return PostMapper.mappingToPostDto(post);
    }

    public List<PostDto> search(String query) {
        List<Post> post = postRepository.searchPost(query);
        return post.stream().map(PostMapper::mappingToPostDto)
                .collect(toList());
    }
}
