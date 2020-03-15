package by.epam.cafe.config;

import by.epam.cafe.controller.interceptor.BasketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private BasketInterceptor basketInterceptor;


//    @Autowired
//    private LogInterceptor logInterceptor;

//    @Bean
//    @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public Basket basketSettion() {
//        return new Basket();
//    }

//    @Bean
//    @Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
//    @Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
//    public BasketInterceptor myInterceptor() {
//        return new BasketInterceptor();
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
                .addResourceLocations("file:///" + uploadPath + "/")
                .setCacheControl(CacheControl.noCache());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(myInterceptor());
        registry.addInterceptor(basketInterceptor).excludePathPatterns("/static/**");
    }
}
