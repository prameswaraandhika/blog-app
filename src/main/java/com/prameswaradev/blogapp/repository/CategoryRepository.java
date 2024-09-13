package com.prameswaradev.blogapp.repository;

import com.prameswaradev.blogapp.model.entity.Category;
import com.prameswaradev.blogapp.model.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
