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
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/admin/posts")
    public String posts(Model model){
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts );
        return "/admin/posts";
    }

}
