package com.prameswaradev.blogapp.controller;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BlogController {

    private final PostService blogService;

    @GetMapping(value = "/")
    public String viewBlogPosts(Model model){
        List<PostDto> posts = blogService.findAllPosts();
        model.addAttribute("posts", posts);
        return "blog/view_posts";
    }
    
}
