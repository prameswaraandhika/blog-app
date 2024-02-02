package com.prameswaradev.blogapp.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private Long id;
    @NotEmpty(message = "Please provide a title")
    private String title;

    @NotEmpty(message = "Please provide a URL")
    private String url;

    @NotEmpty(message = "Please provide content")
    private String content;

    @NotEmpty(message = "Please provide a description")
    private String description;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
