package com.klanvision.backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "seo_data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SEOData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String siteTitle;
    
    @Column(columnDefinition = "TEXT")
    private String metaDescription;
    
    @Column(columnDefinition = "TEXT")
    private String keywords;
    
    private String sitemapUrl;
    private String googleConsoleId;
    
    @Column(columnDefinition = "TEXT")
    private String robotsTxt;
}
