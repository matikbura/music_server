package com.lj.music_server.config;

import com.lj.music_server.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Value("${token.key}")
    private String tokenKey;
    @Value("${user.filepath}")
    private String filePath;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowCredentials(true);
    }
    public void addInterceptors(InterceptorRegistry registry) {
        TokenInterceptor tokenInterceptor = new TokenInterceptor(tokenKey);
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");

    }
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/img/**")
//                .addResourceLocations("file:" + filePath);
//    }
}
