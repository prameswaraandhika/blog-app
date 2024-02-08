package com.prameswaradev.blogapp.controller;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
@RequiredArgsConstructor
public class BlogController {

    private final PostService postService;

    @GetMapping(value = "/")
    public String viewBlogPosts(Model model){
        var posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "blog/view_posts";
    }
    
    @GetMapping(value = "/post/{postUrl}")
    public String viewBlogPostDetail(@PathVariable(value = "postUrl") String postUrl, Model model){
        var postDto = postService.findByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "blog/blog_post";
    }

    @GetMapping(value = "/page/search")
    public String searchPosts(@RequestParam(value = "query") String query,
                              Model model){
        var posts = postService.search(query);
        model.addAttribute("posts", "posts");
        return "blog/view_posts";
    }
    
}
