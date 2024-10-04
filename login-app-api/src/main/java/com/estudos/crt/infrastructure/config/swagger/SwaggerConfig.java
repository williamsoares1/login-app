package com.estudos.crt.infrastructure.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI myOpenApi() {

        Info info = new Info().title("Login App").version("1.0")
                .description("Login App Swagger");

        return new OpenAPI().info(info);

    }
}
