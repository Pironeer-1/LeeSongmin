package com.pironeer.week2_1.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "Pironeer Week2 API 명세서",
                description = "API 명세서",
                version = "v1",
                contact = @Contact(
                        name = "이송민",
                        email = "violet2111@naver.com"
                )
        )
)
@Configuration
public class OpenApiConfig {
}
