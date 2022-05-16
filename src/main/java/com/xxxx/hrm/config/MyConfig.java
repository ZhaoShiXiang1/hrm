package com.xxxx.hrm.config;

import com.xxxx.hrm.interceptors.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//登录拦截
//@Configuration
public class MyConfig implements WebMvcConfigurer {

    @Bean
    public LoginInterceptor createLoginInterceptor(){
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(createLoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login","/user_auth/login","/css/**","/images/**","/js/**","/lib/**");
                //.excludePathPatterns("/index","/user/login","/css/**","/images/**","/js/**","/lib/**");
    }
}
