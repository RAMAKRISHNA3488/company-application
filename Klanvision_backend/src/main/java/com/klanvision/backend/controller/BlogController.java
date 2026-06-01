package com.klanvision.backend.controller;

import com.klanvision.backend.model.BlogPost;
import com.klanvision.backend.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/blogs")
@CrossOrigin(origins = "*")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    public List<BlogPost> getAllPosts() {
        return blogService.getAllPosts();
    }

    @PostMapping
    public BlogPost createPost(@RequestBody BlogPost post) {
        return blogService.savePost(post);
    }

    @PutMapping("/{id}")
    public BlogPost updatePost(@PathVariable Long id, @RequestBody BlogPost post) {
        post.setId(id);
        return blogService.savePost(post);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        blogService.deletePost(id);
        return ResponseEntity.ok().build();
    }
}
