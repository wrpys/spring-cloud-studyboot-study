//package com.newland.summary.interceptor;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * 拦截器
// *
// * @author WRP
// * @since 2019/12/17
// */
//@Configuration
//public class WebAppConfigurer implements WebMvcConfigurer {
//
//    @Autowired
//    private SSOHandlerInterceptor ssoHandlerInterceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        InterceptorRegistration registration = registry.addInterceptor(ssoHandlerInterceptor);
//        // 所有路径都被拦截
//        registration.addPathPatterns("/**");
//        // 添加不拦截路径
//        registration.excludePathPatterns("/js/**",
//                "/summary/*.css", "/summary/fonts/**", "/summary/images/**",
//                "/flows/*.css", "/flows/*.js");
//    }
//
//}
