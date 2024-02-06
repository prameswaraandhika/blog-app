package com.prameswaradev.blogapp.controller;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping(value = "/admin/posts")
    public String posts(Model model) {
        List<PostDto> posts = postService.findAllPosts();
        model.addAttribute("posts", posts);
        return "/admin/posts";
    }

    @GetMapping(value = "/admin/posts/create")
    public String createPostForm(Model model){
        PostDto postDto = new PostDto();
        model.addAttribute("post", postDto);
        return "/admin/create_post";
    }
    
    @PostMapping(value = "/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/create_post";
        }
        String url = getUrl(postDto.getTitle());
        postDto.setUrl(url);
        postService.create(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping(value = "/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") Long postId, Model model){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }

    @PostMapping(value = "/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") Long postId,
                             @Valid @ModelAttribute("post") PostDto postDto,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("post", postDto);
            return "admin/edit_post";
        }
        postDto.setId(postId);
        postService.updatePost(postDto);
        return "redirect:/admin/posts";
    }

    @GetMapping(value = "/admin/posts/{postId}/delete")
    public String deletePost(@PathVariable("postId") Long postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping(value = "/admin/posts/{postUrl}/view")
    public String viewPost(@PathVariable("postUrl") String postUrl,
                           Model model){
        PostDto postDto = postService.findByUrl(postUrl);
        model.addAttribute("post", postDto);
        return "admin/view_post";
    }
    

    private String getUrl(String title) {
        var url = title.trim().toLowerCase().replaceAll("\\s+", "-");
        url = url.replaceAll("[^A-Za-z0-9]", "-");
        return url;
    }


}
