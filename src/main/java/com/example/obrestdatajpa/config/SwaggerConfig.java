package com.example.obrestdatajpa.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Configuración Swagger para generación de documentacion de API REST 
 * 
 * http://localhost:8080/swagger-ui/
 */

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("Spring Boot Library API REST")
            .description("Library api docs")
            .version("v0.0.1")
            .license(new License().name("MIT").url("http://springdoc.org")))
            .externalDocs(new ExternalDocumentation()
            .description("SpringShop Wiki Documentation")
            .url("https://springshop.wiki.github.org/docs"));
    }
}