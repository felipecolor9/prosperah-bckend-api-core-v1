package br.com.prosperah.api.appcore.config;


import br.com.prosperah.api.appcore.handler.ApiHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String pathPattern = "/psph/**";
        String pathPattern2 = "/swagger-ui";
        registry.addInterceptor(new ApiHandlerInterceptor()).excludePathPatterns(pathPattern2).addPathPatterns(pathPattern);
    }


}