package shop.mtcoding.miniproject.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import shop.mtcoding.miniproject.interceptor.WebInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new WebInterceptor())
        // .addPathPatterns("/person/**", "/company/**")
        // .excludePathPatterns("/css/**", "/images/**", "/js/**", "/personLogin",
        // "/companyLogin", "/personJoin1", "/personJoin2",
        // "/companyJoin", "/siteMain", "/customerService");
    }
}