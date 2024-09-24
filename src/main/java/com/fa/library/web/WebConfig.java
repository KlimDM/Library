package com.fa.library.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addBook").setViewName("addbook");
        registry.addViewController("/search").setViewName("search");
    }

    @Override
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/images/**").addResourceLocations("classpath:images/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:static/js/");
    }
}