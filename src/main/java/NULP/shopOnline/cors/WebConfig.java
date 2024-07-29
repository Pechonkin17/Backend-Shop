package NULP.shopOnline.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/user/users")
                .allowedOrigins("http://localhost:8081");

        registry.addMapping("/product/products")
                .allowedOrigins("http://localhost:8081");
    }
}