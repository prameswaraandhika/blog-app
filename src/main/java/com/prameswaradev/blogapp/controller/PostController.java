package com.prameswaradev.blogapp.controller;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.service.CategoryService;
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
    private final CategoryService categoryService;

    @ModelAttribute("categories")
    public void listCategory(Model model){
        model.addAttribute("categories", categoryService.findAll() );
    }
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
        return "admin/create_post";
    }
    
    @PostMapping(value = "/admin/posts")
    public String createPost(@Valid @ModelAttribute("post") PostDto post,
                             BindingResult bindingResult,
                             Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("post", post);
            return "admin/create_post";
        }
        String url = getUrl(post.getTitle());
        post.setUrl(url);
        postService.create(post);
        return "redirect:/admin/posts";
    }

    @GetMapping(value = "/admin/posts/{postId}/edit")
    public String editPostForm(@PathVariable("postId") String postId, Model model){
        PostDto postDto = postService.findPostById(postId);
        model.addAttribute("post", postDto);
        return "admin/edit_post";
    }

    @PostMapping(value = "/admin/posts/{postId}")
    public String updatePost(@PathVariable("postId") String postId,
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
    public String deletePost(@PathVariable("postId") String postId){
        postService.deletePost(postId);
        return "redirect:/admin/posts";
    }

    @GetMapping(value = "/admin/posts/search")
    public String searchPost(@RequestParam(value = "query") String query,
                             Model model){
        List<PostDto> posts = postService.search(query);
        model.addAttribute("posts", posts);
        return "admin/posts";
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
