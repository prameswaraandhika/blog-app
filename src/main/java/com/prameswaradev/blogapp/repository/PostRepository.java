package com.prameswaradev.blogapp.repository;

import com.prameswaradev.blogapp.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
    Optional<?> findByUrl(String url);
}
