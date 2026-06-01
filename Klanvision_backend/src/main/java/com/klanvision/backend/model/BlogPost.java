package com.klanvision.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "blog_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    
    @Column(columnDefinition = "TEXT")
    private String excerpt;
    
    @Column(columnDefinition = "LONGTEXT")
    private String content;
    
    private String category;
    private String author;
    private String date;
    private String readTime;
    private String status;
    private String image;
    private int views;
    @Column(name = "author_link")
    private String authorLink;
}
