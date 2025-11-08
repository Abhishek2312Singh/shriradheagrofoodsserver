//package com.srap.Shree.Radhe.Agro.Foods.controller;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//    @Configuration
//    public class WebConfig {
//
//        @Bean
//        public WebMvcConfigurer corsConfigurer() {
//            return new WebMvcConfigurer() {
//                @Override
//                public void addCorsMappings(CorsRegistry registry) {
//                    registry.addMapping("/**")
//                            .allowedOrigins("https://shriradheagrofoods.com","http://localhost:80") // your frontend domain
//                            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                            .allowedHeaders("*")
//                            .allowCredentials(true);
//                }
//            };
//        }
//    }
//
//
