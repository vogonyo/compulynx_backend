//package com.compulyx.backend.configs;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springdoc.core.GroupedOpenApi;
//import org.springdoc.core.SwaggerUiConfigParameters;
//
//@Configuration
//public class OpenApiConfig implements WebMvcConfigurer {
//
//    @Bean
//    public GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("public-api")
//                .pathsToMatch("/api/**") // Adjust the path pattern as needed
//                .build();
//    }
//
//    @Bean
//    public SwaggerUiConfigParameters swaggerUiConfigParameters() {
//        SwaggerUiConfigParameters parameters = new SwaggerUiConfigParameters();
//        parameters.setDefaultModelExpandDepth(5);
//        // Add other configuration options as needed
//        return parameters;
//    }
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        registry.addResourceHandler("/swagger-ui.html**").addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//    }
//}
