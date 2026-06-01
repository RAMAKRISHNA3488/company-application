package com.klanvision.backend.service;

import com.klanvision.backend.model.SEOData;
import com.klanvision.backend.repository.SEODataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SEOService {

    @Autowired
    private SEODataRepository seoDataRepository;

    public SEOData getSEOData() {
        return seoDataRepository.findAll().stream().findFirst().orElse(new SEOData());
    }

    public SEOData updateSEOData(SEOData seoData) {
        // Ensure only one SEO data record exists
        SEOData existing = seoDataRepository.findAll().stream().findFirst().orElse(null);
        if (existing != null) {
            seoData.setId(existing.getId());
        }
        return seoDataRepository.save(seoData);
    }
}
