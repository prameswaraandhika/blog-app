package com.prameswaradev.blogapp.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE post SET status_record = 'INACTIVE' WHERE id=?")
@Where(clause = "status_record = 'ACTIVE'")
@Table(name = "post")@SQLDelete(sql = "UPDATE category SET status_record = 'INACTIVE' WHERE id=?")
@Entity
public class Post extends BaseEntity{
    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @NotEmpty
    private String title;
    @NotEmpty
    private String url;
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String content;
    @NotEmpty
    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Comment> comments = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "post_reading_list",
                joinColumns = {@JoinColumn(name = "post_id")},
                inverseJoinColumns = {@JoinColumn(name = "reading_list_id")})
    private List<Post> posts = new ArrayList<>();
}
