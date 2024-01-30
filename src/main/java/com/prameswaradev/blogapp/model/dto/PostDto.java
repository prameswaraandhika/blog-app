package com.prameswaradev.blogapp.model.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class PostDto {
    private Long id;
    private String title;
    private String url;
    private String content;
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
