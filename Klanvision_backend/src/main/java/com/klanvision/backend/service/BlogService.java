package com.klanvision.backend.service;

import com.klanvision.backend.model.BlogPost;
import com.klanvision.backend.repository.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogPostRepository blogPostRepository;

    public List<BlogPost> getAllPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost savePost(BlogPost post) {
        return blogPostRepository.save(post);
    }

    public void deletePost(Long id) {
        blogPostRepository.deleteById(id);
    }

    public BlogPost getPostById(Long id) {
        return blogPostRepository.findById(id).orElse(null);
    }
}
