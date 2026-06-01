package com.klanvision.backend.controller;

import com.klanvision.backend.model.SEOData;
import com.klanvision.backend.service.SEOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seo")
@CrossOrigin(origins = "*")
public class SEOController {

    @Autowired
    private SEOService seoService;

    @GetMapping
    public SEOData getSEOData() {
        return seoService.getSEOData();
    }

    @PostMapping
    public SEOData updateSEOData(@RequestBody SEOData seoData) {
        return seoService.updateSEOData(seoData);
    }
}
