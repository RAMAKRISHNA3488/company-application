package com.klanvision.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class KlanvisionBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(KlanvisionBackendApplication.class, args);
	}

	@org.springframework.context.annotation.Bean
	public org.springframework.boot.CommandLineRunner seedDatabase(
			com.klanvision.backend.repository.AdminUserRepository adminRepo,
			com.klanvision.backend.repository.JobListingRepository jobRepo,
			com.klanvision.backend.repository.ProjectRepository projectRepo,
			com.klanvision.backend.repository.BlogPostRepository blogRepo,
			com.klanvision.backend.repository.SEODataRepository seoRepo) {
		return args -> {
			// 1. Seed Admin Users
			if (adminRepo.findByEmail("kirankumarmoopuri@klanvision.com").isEmpty()) {
				// Admin 1
				com.klanvision.backend.model.AdminUser kiran = new com.klanvision.backend.model.AdminUser();
				kiran.setUsername("kirankumarmoopuri");
				kiran.setEmail("kirankumarmoopuri@klanvision.com");
				kiran.setName("Kiran Kumar Moopuri");
				kiran.setPassword("Klanph$@0315");
				kiran.setRole("Super Admin");
				kiran.setPermissions(java.util.Arrays.asList("Dashboard", "Users", "Projects", "Blogs", "SEO",
						"Messages", "Settings", "Activity Log"));
				kiran.set2FAEnabled(false);
				kiran.setSecret2FA("JBSWY3DPEBLW64TMMQ");
				adminRepo.save(kiran);
			}
			// 5. Seed SEO Data
			if (seoRepo.count() == 0) {
				com.klanvision.backend.model.SEOData seo = new com.klanvision.backend.model.SEOData();
				seo.setSiteTitle("Klanvision IT Solutions");
				seo.setMetaDescription(
						"Professional and Expert Engineers. Certified IT products trusted by enterprise clients worldwide.");
				seo.setKeywords("Engineering, Design, Software Development, IT Consulting, API Integration");
				seo.setSitemapUrl("https://klanvision.com/sitemap.xml");
				seo.setGoogleConsoleId("G-KLANVISION");
				seo.setRobotsTxt("User-agent: *\nAllow: /");
				seoRepo.save(seo);
			}
		};
	}

}
