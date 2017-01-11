package com.knongdai.tinh.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class PsarnetWebMvcConfiguration extends WebMvcConfigurerAdapter{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		
		registry.addViewController("/login").setViewName("/login");
		
		registry.addViewController("/swagger").setViewName("/swagger");
		
		// Not Found...
		registry.addViewController("/").setViewName("/error/404");
		
		// Errors
		registry.addViewController("/401").setViewName("/error/401");
		registry.addViewController("/access-denied").setViewName("/error/403");
		

	}
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedMethods("GET","POST","DELETE","PUT","OPTIONS","PATCH")
				.allowedOrigins("*");
	}
}
