package com.prameswaradev.blogapp.repository;

import com.prameswaradev.blogapp.model.dto.PostDto;
import com.prameswaradev.blogapp.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url);

    @Query("SELECT p FROM Post p WHERE " +
            "p.title LIKE CONCAT('%', :query, '%') OR " +
            "p.description LIKE CONCAT ('%', :query, '%')")
    List<Post> searchPost(String query);
}
