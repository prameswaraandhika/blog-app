package com.prameswaradev.blogapp.model.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDto {
    private String id;
    private String category;
    @NotEmpty(message = "Please provide a title")
    private String title;

    private String url;

    @NotEmpty(message = "Please provide content")
    private String content;

    @NotEmpty(message = "Please provide a description")
    private String description;

    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}
